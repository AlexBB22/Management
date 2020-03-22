package nl.tudelft.oopp.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column (name = "role_id")
    @GeneratedValue
    //for tests, not doing auto generation
    //Admin = 3 Teacher = 2, Staff = 1, Student = 0
    private int roleId;

    @NotNull
    @Size(max = 255)
    @Column(name = "role_name")
    private String roleName;

    //    @OneToMany(mappedBy = "role")
    //    private List<User> users = new ArrayList<User>();
    //    //Constructors + Getters/Setters
    //    public Role() {}

    public Role() {
    }

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String toString() {
        return "Role id is: " + this.roleId + " Role name is: " + this.roleName;
    }
}
