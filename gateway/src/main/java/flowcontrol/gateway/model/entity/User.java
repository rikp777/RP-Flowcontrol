package flowcontrol.gateway.model.entity;

import flowcontrol.gateway.model.entity.base.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "user")
public class User extends DateAudit {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    private Long id;

    //@NaturelId //Looup Id
    @Column(name = "email", unique = true)
    @NotBlank(message = "User email cannot be null")
    private String email;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Username cannot be blank")
    @NotNull(message =  "Username cannot be null")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Column(name = "active", nullable = false)
    private Boolean isActive;

    @Column(name = "email_verified", nullable = false)
    private Boolean isEmailVerified;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "user_authority",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "role_id")
            }
    )
    private Set<Role> roles = new HashSet<>();

    public User(){
        super();
    }

    public User(User user){
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        isActive = user.getIsActive();
        isEmailVerified = user.getIsEmailVerified();
        roles = user.getRoles();
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
}
