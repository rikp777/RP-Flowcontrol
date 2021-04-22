package flowcontrol.gateway.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import flowcontrol.gateway.model.entity.base.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import flowcontrol.gateway.validation.NullOrNotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "user")
@ToString
public class User extends DateAudit {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    private Long id;

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


    public void markVerificationConfirmed(){
        this.setIsEmailVerified(true);
    }
}
