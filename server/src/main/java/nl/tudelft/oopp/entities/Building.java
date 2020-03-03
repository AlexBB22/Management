package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import nl.tudelft.oopp.entities.Bike;
import nl.tudelft.oopp.entities.Restaurant;



@Entity
@Table(name = "building")
public class Building {

    @Id
    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "non_reservable_space")
    private boolean nonReservableSpace;

    @Column(name = "car_parking_spaces")
    private int carParkingSpaces;

    @Column(name = "description")
    private String description;

    @Column(name = "opening_time")
    private Time opening;

    @Column (name = "closing_time")
    private Time closing;

    @OneToMany(mappedBy = "building",  cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<Room>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    @OneToMany(mappedBy = "building",  cascade = CascadeType.ALL)
    private List<Bike> bikes = new ArrayList<Bike>();

    public Building(){
    }

    /**.
     * constructor for building
     * @param buildingName name of the building
     * @param nonReservableSpace amount of nonReservableSpace
     * @param carParkingSpaces amount of carParkingSpace
     * @param description extra information about the building
     * @param opening the time at which the building opens
     * @param closing the time at which the building closes
     * @Autor Scott Jochems
     */
    public Building(String buildingName, boolean nonReservableSpace, int carParkingSpaces, String description,Time opening, Time closing) {
        this.buildingName = buildingName;
        this.nonReservableSpace = nonReservableSpace;
        this.carParkingSpaces = carParkingSpaces;
        this.description = description;
        this.opening = opening;
        this.closing = closing;
    }

    public String getBuilding_Name() {
        return buildingName;
    }

    public void setBuilding_name(String buildingName) {
        this.buildingName = buildingName;
    }

    public boolean isNon_reservable_space() {
        return nonReservableSpace;
    }

    public void setNon_reservable_space(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    public int getCar_parking_spaces() {
        return carParkingSpaces;
    }

    public void setCar_parking_spaces(int carParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getOpening() {
        return opening;
    }

    public void setOpening(Time opening) {
        this.opening = opening;
    }

    public Time getClosing() {
        return closing;
    }

    public void setClosing(Time closing) {
        this.closing = closing;
    }


    /*
    Room related methods
     */
    @JsonIgnore
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setBuilding(this);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.setBuilding(null);
    }

}

