package flowcontrol.gateway.controllers;


import flowcontrol.gateway.annotation.CurrentUser;
import flowcontrol.gateway.controllers.advise.AuthControllerAdvice;
import flowcontrol.gateway.event.*;
import flowcontrol.gateway.exception.*;
import flowcontrol.gateway.model.entity.CustomUserDetails;
import flowcontrol.gateway.model.entity.RefreshToken;
import flowcontrol.gateway.model.general.EmailVerificationToken;
import flowcontrol.gateway.model.request.*;
import flowcontrol.gateway.model.response.ApiResponse;
import flowcontrol.gateway.model.response.JwtAuthenticationResponse;
import flowcontrol.gateway.security.JwtTokenProvider;
import flowcontrol.gateway.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/signal")
    public String signal(){
        return "Hello!";
    }

    @GetMapping("/checkEmailInUse")
    public ResponseEntity checkEmailInUse(@RequestParam("email") String email){
        Boolean emailExists = authService.emailAlreadyExists(email);
        return ResponseEntity.ok(new ApiResponse(emailExists.toString(), true));
    }

    @GetMapping("/checkUsernameInUse")
    public ResponseEntity checkUsernameInUse(@RequestParam("username") String username){
        Boolean usernameExists = authService.usernameAlreadyExists(username);
        return ResponseEntity.ok(new ApiResponse(usernameExists.toString(), true));
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser(@CurrentUser CustomUserDetails customUserDetails, @Valid @RequestBody LogOutRequest logOutRequest){
        authService.logOutUser(customUserDetails, logOutRequest);

        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();

        OnUserLogOutSuccessEvent onUserLogOutSuccessEvent = new OnUserLogOutSuccessEvent(customUserDetails.getEmail(), credentials.toString(), logOutRequest);
        applicationEventPublisher.publishEvent(onUserLogOutSuccessEvent);

        return ResponseEntity.ok(new ApiResponse("Log out successfully", true));
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authService.authenticateUser(loginRequest)
                .orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info("Logged in User returned [ API ] : " + customUserDetails.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authService.createAndPersistRefreshTokenForDevice(authentication, loginRequest)
                .map(RefreshToken::getToken)
                .map(refreshToken -> {
                    String jwtToken = authService.generateToken(customUserDetails);
                    return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken, refreshToken, tokenProvider.getJwtExpirationInMs()));
                })
                .orElseThrow(() -> new UserLoginException("Couldn't create refresh token form : [" + loginRequest + "]"));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        return authService.registerUser(registrationRequest)
                .map(user -> {
                    UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/auth/registrationConfirmation");
                    OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent = new OnUserRegistrationCompleteEvent(user, urlBuilder);
                    applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent);
                    log.info("Registered User returned [ API ] : " + user);
                    return ResponseEntity.ok(new ApiResponse("User registered successfully. Check your email for verification", true));
                })
                .orElseThrow(() -> new UserRegistrationException(registrationRequest.getEmail(), "Missing user object in database"));
    }

    @PostMapping("/password/resetlink")
    public ResponseEntity resetLink(@Valid @RequestBody PasswordResetLinkRequest passwordResetLinkRequest){

        return authService.generatePasswordResetToken(passwordResetLinkRequest)
                .map(passwordResetToken -> {
                    UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path("/password/reset");
                    OnGenerateResetLinkEvent generateResetLinkEvent = new OnGenerateResetLinkEvent(passwordResetToken, urlBuilder);

                    applicationEventPublisher.publishEvent(generateResetLinkEvent);
                    return ResponseEntity.ok(new ApiResponse("Password reset link sent successfully", true));
                })
                .orElseThrow(() -> new PasswordResetLinkException(passwordResetLinkRequest.getEmail(), "Couldn't create a valid token"));
    }

    @PostMapping("/password/reset")
    public ResponseEntity resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest){
        return authService.resetPassword(passwordResetRequest)
                .map(changedUser -> {
                    OnUserAccountChangeEvent onUserAccountChangeEvent = new OnUserAccountChangeEvent(changedUser, "Reset Password", "Changed Successfully");
                    applicationEventPublisher.publishEvent(onUserAccountChangeEvent);
                    return ResponseEntity.ok(new ApiResponse("Password changed succesfully", true));
                })
                .orElseThrow(() -> new PasswordResetException(passwordResetRequest.getToken(), "Error in resetting password"));
    }

    @GetMapping("/registrationConfirmation")
    public ResponseEntity confirmRegistration(@RequestParam("token") String token){
        return authService.confirmEmailRegistration(token)
                .map(user -> ResponseEntity.ok(new ApiResponse("User verified successfully", true)))
                .orElseThrow(() -> new TokenInvalidRequestException("Email Verification token", token, "Failed to confirm. Please generate a new email verification request"));
    }

    @GetMapping("/resendRegistrationToken")
    public ResponseEntity resendRegistrationToken(@RequestParam("token") String existingToken){
        EmailVerificationToken newEmailToken = authService.recreateRegistrationToken(existingToken)
                .orElseThrow(() -> new TokenInvalidRequestException("Email Verification token", existingToken, "user is already registered, No need to re-generate token"));

        return Optional.ofNullable(newEmailToken.getUser())
                .map(registeredUser -> {
                    UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/registrationConfirmation");
                    OnRegenerateEmailVerificationEvent regenerateEmailVerificationEvent = new OnRegenerateEmailVerificationEvent(registeredUser, urlBuilder, newEmailToken);
                    applicationEventPublisher.publishEvent(regenerateEmailVerificationEvent);
                    return ResponseEntity.ok(new ApiResponse("Email verification resent successfully", true));
                })
                .orElseThrow(() -> new TokenInvalidRequestException("Email Verification Token", existingToken, "No user associated with this request. Re-verification denied"));
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshJwtToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest){

        return authService.refreshJwtToken(tokenRefreshRequest)
                .map(updatedToken -> {
                    String refreshToken = tokenRefreshRequest.getRefreshToken();
                    log.info("Created new Jwt auth token " + updatedToken);
                    return ResponseEntity.ok(new JwtAuthenticationResponse(updatedToken, refreshToken, tokenProvider.getJwtExpirationInMs()));
                })
                .orElseThrow(() -> new TokenRefreshException(tokenRefreshRequest.getRefreshToken(), "Unexpected  error during token refresh. Please logout and login again"));
    }
}
