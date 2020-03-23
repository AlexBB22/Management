package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;




@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "user_id")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_password")
    private String userPassword;

    //Mapping to a role, creating a FK here to point to Role table PK
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "role_fk", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "userFk", cascade = CascadeType.ALL)
    private List<RoomReservation> roomReservations = new ArrayList<RoomReservation>();

    @OneToMany(mappedBy = "bikeUserFk")
    private List<BikeReservation> bikeReservations = new ArrayList<BikeReservation>();

    //Constructors + Getters/Setters
    public User() {
    }

    /**
     * Constructor for a user entity.
     * @param userId - the users id
     * @param email - the users email
     * @param userName - the users username
     * @param userPassword - the users password
     */
    public User(int userId, String email, String userName, String userPassword) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return userName;
    }

    public void setUser_name(String userName) {
        this.userName = userName;
    }

    public String getUser_password() {
        return userPassword;
    }

    public void setUser_password(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @JsonManagedReference(value = "userRoomReservations")
    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }

    public void addRoomReservation(RoomReservation roomReservation) {
        this.roomReservations.add(roomReservation);
    }

    public void removeRoomReservation(RoomReservation roomReservation) {
        this.roomReservations.remove(roomReservation);
    }

    public String toString() {
        return "user_id: " + this.userId + " , email: " + this.email
                + " , user_name: " + this.userName + " , user_password: " + this.userPassword + " , role_fk: " + this.getRole().getRole_id();
    }

    public List<BikeReservation> getBikeReservations() {
        return bikeReservations;
    }

    public void setBikeReservations(List<BikeReservation> bikeReservations) {
        this.bikeReservations = bikeReservations;
    }

    public void addBikeReservation(BikeReservation bikeReservation) {
        this.bikeReservations.add(bikeReservation);
    }
}
