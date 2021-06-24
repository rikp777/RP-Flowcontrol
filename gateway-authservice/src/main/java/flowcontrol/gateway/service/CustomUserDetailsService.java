package flowcontrol.gateway.service;

import flowcontrol.gateway.model.entity.CustomUserDetails;
import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> dbUser = userRepository.findByEmail(email);

        log.info("Fetched user : " + dbUser + " by " + email);
        return dbUser
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user email in the database form " + email));
    }

    public UserDetails loadUserById(Long id){
        Optional<User> dbUser = userRepository.findById(id);

        log.info("Fetched user : " + dbUser + " by " + id);

        return dbUser
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user id in the database for " + id));
    }
}
