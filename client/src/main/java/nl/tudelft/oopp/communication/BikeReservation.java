package nl.tudelft.oopp.communication;

import java.sql.Date;

public class BikeReservation {
    private int id;
    private User bikeUserFk;
    private Bike bikeFk;
    private Date day;
    private Building building;

    public BikeReservation() {
    }

    public BikeReservation(Date day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBike_user_fk() {
        return bikeUserFk;
    }

    public void setBike_user_fk(User bikeUserFk) {
        this.bikeUserFk = bikeUserFk;
    }

    public Bike getBike_fk() {
        return bikeFk;
    }

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
