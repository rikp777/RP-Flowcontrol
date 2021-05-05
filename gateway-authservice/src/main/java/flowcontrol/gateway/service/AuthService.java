package flowcontrol.gateway.service;

import flowcontrol.gateway.annotation.CurrentUser;
import flowcontrol.gateway.exception.*;
import flowcontrol.gateway.model.entity.*;
import flowcontrol.gateway.model.general.EmailVerificationToken;
import flowcontrol.gateway.model.request.*;
import flowcontrol.gateway.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final UserDeviceService userDeviceService;
    private final PasswordResetTokenService passwordResetTokenService;

    public Optional<User> registerUser(RegistrationRequest newRegistrationRequest){
        String newRegistrationRequestEmail = newRegistrationRequest.getEmail();
        if(emailAlreadyExists(newRegistrationRequestEmail)){
            log.error("Email already exists: " + newRegistrationRequestEmail);
            throw new ResourceAlreadyInUseException("Email", "Adress", newRegistrationRequestEmail);
        }
        log.info("Trying to registration new user [" + newRegistrationRequestEmail + "]");
        User newUser = userService.createUser(newRegistrationRequest);
        User registeredNewUser = userService.save(newUser);
        return Optional.ofNullable(registeredNewUser);
    }

    public Boolean emailAlreadyExists(String email){
        return userService.existsByEmail(email);
    }

    public Boolean usernameAlreadyExists(String username){
        return userService.existsByUsername(username);
    }

    public Optional<Authentication> authenticateUser(LoginRequest loginRequest){
        return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())));
    }

    public Optional<User> confirmEmailRegistration(String emailToken){
        EmailVerificationToken emailVerificationToken = emailVerificationTokenService.findByToken(emailToken)
                .orElseThrow(() -> new ResourceNotFoundException("Token", "Email verification", emailToken));

        User registeredUser = emailVerificationToken.getUser();
        if(registeredUser.getIsEmailVerified()){
            log.info("User [" + emailToken + "] already registered");
            return Optional.of(registeredUser);
        }

        emailVerificationTokenService.verifyExpiration(emailVerificationToken);
        emailVerificationToken.setConfirmedStatus();
        emailVerificationTokenService.save(emailVerificationToken);

        registeredUser.markVerificationConfirmed();
        userService.save(registeredUser);
        return Optional.of(registeredUser);
    }

    public Optional<EmailVerificationToken> recreateRegistrationToken(String existingToken){
        EmailVerificationToken emailVerificationToken = emailVerificationTokenService.findByToken(existingToken)
                .orElseThrow(() -> new ResourceNotFoundException("Token", "Existing email verification", existingToken));

        if(emailVerificationToken.getUser().getIsEmailVerified()){
            return Optional.empty();
        }
        return Optional.ofNullable(emailVerificationTokenService.updateExistingTokenWithNameAndExpiry(emailVerificationToken));
    }

    public Boolean currentPasswordMatches(User currentUser, String password){
        return passwordEncoder.matches(password, currentUser.getPassword());
    }


    public Optional<User> updatePassword(CustomUserDetails customUserDetails, UpdatePasswordRequest updatePasswordRequest){
        String email = customUserDetails.getEmail();
        User currentUser = userService.findByEmail(email)
                .orElseThrow(() -> new PasswordUpdateException(email, "No matching user found"));

        if(!currentPasswordMatches(currentUser, updatePasswordRequest.getOldPassword())){
            log.info("Current password is invalid for [" + currentUser.getPassword() + "]");
            throw new PasswordUpdateException(currentUser.getEmail(), "Invalid current password");
        }

        String newPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        currentUser.setPassword(newPassword);
        userService.save(currentUser);
        return Optional.of(currentUser);
    }

    public String generateToken(CustomUserDetails customUserDetails){
        return tokenProvider.generateToken(customUserDetails);
    }

    public String generateTokenFromUserId(Long userId){
        return tokenProvider.generateTokenFromUserId(userId);
    }

    public Optional<RefreshToken> createAndPersistRefreshTokenForDevice(Authentication authentication, LoginRequest loginRequest){
        User currentUser = (User) authentication.getPrincipal();
        userDeviceService.findByUserId(currentUser.getId())
                .map(UserDevice::getRefreshToken)
                .map(RefreshToken::getId)
                .ifPresent(refreshTokenService::deleteById);

        UserDevice userDevice = userDeviceService.createUserDevice(loginRequest.getDeviceInfo());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken();
        userDevice.setUser(currentUser);
        userDevice.setRefreshToken(refreshToken);
        refreshToken.setUserDevice(userDevice);
        refreshToken = refreshTokenService.save(refreshToken);
        return Optional.ofNullable(refreshToken);
    }

    public Optional<String> refreshJwtToken(TokenRefreshRequest tokenRefreshRequest){
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return Optional.of(refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshToken -> {
                    refreshTokenService.verifyExpiration(refreshToken);
                    userDeviceService.verifyRefreshAvailability(refreshToken);
                    refreshTokenService.increaseCount(refreshToken);
                    return refreshToken;
                })
                .map(RefreshToken::getUserDevice)
                .map(UserDevice::getUser)
                .map(User::getId)
                .map(this::generateTokenFromUserId))
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Missing refresh token in database.Please login again"));
    }

    public Optional<PasswordResetToken> generatePasswordResetToken(PasswordResetLinkRequest passwordResetLinkRequest) {
        String email = passwordResetLinkRequest.getEmail();
        return userService.findByEmail(email)
                .map(user -> {
                    PasswordResetToken passwordResetToken = passwordResetTokenService.createToken();
                    passwordResetToken.setUser(user);
                    passwordResetTokenService.save(passwordResetToken);
                    return Optional.of(passwordResetToken);
                })
                .orElseThrow(() -> new PasswordResetLinkException(email, "No matching user found for the given request"));
    }

    public Optional<User> resetPassword(PasswordResetRequest passwordResetRequest) {
        String token = passwordResetRequest.getToken();
        PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Password Reset Token", "Token Id", token));

        passwordResetTokenService.verifyExpiration(passwordResetToken);
        final String encodedPassword = passwordEncoder.encode(passwordResetRequest.getPassword());

        return Optional.of(passwordResetToken)
                .map(PasswordResetToken::getUser)
                .map(user -> {
                    user.setPassword(encodedPassword);
                    userService.save(user);
                    return user;
                });
    }

    public void logOutUser(@CurrentUser CustomUserDetails currentUser, LogOutRequest logOutRequest){
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();

        UserDevice userDevice = userDeviceService.findByUserId(currentUser.getId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "Invalid device Id supplied. No matching device found for the given user"));

        log.info("Removing refresh token associated with device [" + userDevice + "]");
        refreshTokenService.deleteById(userDevice.getRefreshToken().getId());
    }
}
