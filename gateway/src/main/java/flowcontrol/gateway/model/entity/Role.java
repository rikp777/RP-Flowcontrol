package flowcontrol.gateway.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.gateway.model.entity.enumeration.RoleType;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public boolean isAdminRole(){
        return this.roleType.equals(RoleType.ROLE_ADMIN);
    }
}
