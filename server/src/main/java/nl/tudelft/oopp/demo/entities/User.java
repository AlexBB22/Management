package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    private int user_id;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_name")
    private String user_name;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_password")
    private String user_password;

    //Mapping to a role, creating a FK here to point to Role table PK
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_fk", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user_fk")
    private List<RoomReservation> roomReservations = new ArrayList<RoomReservation>();

    //Constructors + Getters/Setters
    public User() {}
    public User(int user_id, String email, String user_name, String user_password) {
        this.user_id = user_id;
        this.email = email;
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }
}
