package flowcontrol.gateway.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import flowcontrol.gateway.model.entity.base.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import flowcontrol.gateway.validation.NullOrNotBlank;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "user")

@Builder

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @NaturalId //Lookup Id
    @Column(name = "email", unique = true)
    @NotBlank(message = "User email cannot be null")
    private String email;

    @Column(name = "username", unique = true)
    @NullOrNotBlank(message = "Username can be null but not blank")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "first_name")
    @NullOrNotBlank(message = "First name can be null but not blank")
    private String firstName;

    @Column(name = "last_name")
    @NullOrNotBlank(message = "Last name can be null but not blank")
    private String lastName;

    @Column(name = "active", nullable = false)
    private Boolean isActive;

    @Column(name = "email_verified", nullable = false)
    private Boolean isEmailVerified;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "user_authority",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    public User(User user){
        super(user.getId());
        username = user.getUsername();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        isActive = user.getIsActive();
        isEmailVerified = user.getIsEmailVerified();
        roles = user.getRoles();
    }
    public User(UUID id){
        super(id);
    }

    public void addRole(Role role){
        roles.add(role);
        role.getUsers().add(this);
    }
    public void addRoles(Set<Role> roles){
        roles.forEach(this::addRole);
    }

    public void removeRole(Role role){
        roles.remove(role);
        role.getUsers().remove(this);
    }


    public void markVerificationConfirmed(){
        this.setIsEmailVerified(true);
    }
}
