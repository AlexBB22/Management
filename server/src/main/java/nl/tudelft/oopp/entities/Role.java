package nl.tudelft.oopp.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role")
public class Role {
    @Id
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

    public int getRole_id() {
        return roleId;
    }

    public void setRole_id(int roleId) {
        this.roleId = roleId;
    }

    public String getRole_name() {
        return roleName;
    }

    public void setRole_name(String roleName) {
        this.roleName = roleName;
    }

    public String toString() {
        return "Role id is: " + this.roleId + " Role name is: " + this.roleName;
    }
}
