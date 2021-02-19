package flowcontrol.gateway.service;

import flowcontrol.gateway.exception.ResourceAlreadyInUseException;
import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.model.general.EmailVerificationToken;
import flowcontrol.gateway.model.request.LoginRequest;
import flowcontrol.gateway.model.request.RegistrationRequest;
import flowcontrol.gateway.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Optional<Authentication> authentication(LoginRequest loginRequest){
        return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())));
    }

    public Optional<User> confirmEmailRegistration(String emailToken){
        EmailVerificationToken emailVerificationToken = emailVerificationTokenService.findByToken(emailToken)
    }
}
