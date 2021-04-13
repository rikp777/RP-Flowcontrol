package flowcontrol.gateway.model.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(final User user){
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getIsEmailVerified();
    }
}
