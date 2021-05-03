package flowcontrol.gateway.controllers;

import flowcontrol.gateway.annotation.CurrentUser;
import flowcontrol.gateway.event.OnUserAccountChangeEvent;
import flowcontrol.gateway.event.OnUserLogOutSuccessEvent;
import flowcontrol.gateway.exception.PasswordUpdateException;
import flowcontrol.gateway.model.entity.CustomUserDetails;
import flowcontrol.gateway.model.request.LogOutRequest;
import flowcontrol.gateway.model.request.UpdatePasswordRequest;
import flowcontrol.gateway.model.response.ApiResponse;
import flowcontrol.gateway.service.AuthService;
import flowcontrol.gateway.service.UserService;
//import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/users")
//@Api(value = "User Rest APi - Endpoints")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;


    @GetMapping("/me")
    public ResponseEntity getUserProfile(@AuthenticationPrincipal CustomUserDetails user){
        log.info(user.getEmail() + " has role: " + user.getRoles());
        //mag production getall
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok("Hello. this is about me " + user.getUsername());
    }


    @GetMapping("/admins")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity getAllAdmins(){
       log.info("Inside secured resource with admin");
       return ResponseEntity.ok("Hello. this is about admins");
    }

    @PostMapping("/password/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateUserPassword(@CurrentUser CustomUserDetails customUserDetails, @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest){
        return authService.updatePassword(customUserDetails, updatePasswordRequest)
                .map(updatedUser -> {
                    OnUserAccountChangeEvent onUserAccountChangeEvent =new OnUserAccountChangeEvent(updatedUser, "Update Password", "Change successful");
                    applicationEventPublisher.publishEvent(onUserAccountChangeEvent);
                    return ResponseEntity.ok(new ApiResponse("Password changed successfully", true));
                })
                .orElseThrow(() -> new PasswordUpdateException("--Empty--", "No such user present"));
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser(@CurrentUser CustomUserDetails customUserDetails, @Valid @RequestBody LogOutRequest logOutRequest){
        userService.logOutUser(customUserDetails, logOutRequest);

        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();

        OnUserLogOutSuccessEvent onUserLogOutSuccessEvent = new OnUserLogOutSuccessEvent(customUserDetails.getEmail(), credentials.toString(), logOutRequest);
        applicationEventPublisher.publishEvent(onUserLogOutSuccessEvent);

        return ResponseEntity.ok(new ApiResponse("Log out successfully", true));
    }
}
