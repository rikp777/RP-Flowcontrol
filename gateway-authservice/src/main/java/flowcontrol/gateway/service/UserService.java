package flowcontrol.gateway.service;

import flowcontrol.gateway.annotation.CurrentUser;
import flowcontrol.gateway.controllers.AuthController;
import flowcontrol.gateway.exception.UserLogoutException;
import flowcontrol.gateway.model.entity.CustomUserDetails;
import flowcontrol.gateway.model.entity.Role;
import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.model.entity.UserDevice;
import flowcontrol.gateway.model.request.LogOutRequest;
import flowcontrol.gateway.model.request.RegistrationRequest;
import flowcontrol.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserDeviceService userDeviceService;
    private final RefreshTokenService refreshTokenService;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }


    public User createUser(RegistrationRequest registerRequest){
        User newUser = new User();

        Boolean isNewUserAsAdmin = registerRequest.getRegisterAsAdmin();

        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setUsername(registerRequest.getEmail());
        newUser.addRoles(this.getRolesForNewUser(isNewUserAsAdmin));
        newUser.setIsActive(true);
        newUser.setIsEmailVerified(false);
        return newUser;
    }

    private Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin){
        Set<Role> newUserRoles = new HashSet<>(roleService.findAll());

        if(!isToBeMadeAdmin){
            newUserRoles.removeIf(Role::isAdminRole);
        }
        log.info("Setting user roles: [" + newUserRoles + "]");

        return newUserRoles;
    }



}
