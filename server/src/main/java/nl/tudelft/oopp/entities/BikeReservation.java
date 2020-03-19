package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bike_reservation")
public class BikeReservation {

    @Id
    @Column(name = "bike_reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User bikeUserFk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bike_id")
    private Bike bikeFk;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "day")
    private Date day;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "buildingName")
    private Building building;

    public BikeReservation() {
    }

    public BikeReservation(Date day) {
        this.day = day;
    }

    /**
     * Retrieves the id of the bike reservation.
     * @return - the id of the reservation
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the infortmation about a user who reserved a bike.
     * @return - the user
     */
    public User getBike_user_fk() {
        return bikeUserFk;
    }

    /**
     * Sets the characteristics of the user to a given value.
     * @param bikeUserFk - the value the user should be set to
     */
    public void setBike_user_fk(User bikeUserFk) {
        this.bikeUserFk = bikeUserFk;
    }

    /**
     * Retrieves informatino about the bike that has been reserved.
     * @return the bike that has been reserved
     */
    public Bike getBike_fk() {
        return bikeFk;
    }

    /**
     * Sets the bike that has been reserved to a given value.
     * @param bikeFk - the value the bike should be set to
     */
    public void setBike_fk(Bike bikeFk) {
        this.bikeFk = bikeFk;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * The representation of a bike reservation as a string.
     * @author - Sartori Kendra
     * @return - a string containing information about a bike reservation
     */
    public String toString() {
        return "Bike reservation unique ID: "
             + this.id + ", day: "
             + this.day + ", location: "
             + this.building.getBuilding_Name();
    }

}
