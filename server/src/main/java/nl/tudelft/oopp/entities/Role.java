package nl.tudelft.oopp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    //for tests, not doing auto generation
    //Admin = 3 Teacher = 2, Staff = 1, Student = 0
    private int role_id;

    @NotNull
    @Size(max = 255)
    @Column(name = "role_name")
    private String role_name;

//    @OneToMany(mappedBy = "role")
//    private List<User> users = new ArrayList<User>();
//    //Constructors + Getters/Setters
//    public Role() {}

    public Role() {}

    public Role(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String toString() {
        return "Role id is: " + this.role_id + " Role name is: " + this.role_name;
    }
}
