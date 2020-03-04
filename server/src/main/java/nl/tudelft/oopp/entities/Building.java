package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
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

    /**
     *
     * @return the name of the building
     */
    public String getBuilding_Name() {
        return buildingName;
    }

    /**
     * Sets the name of a building to a given value
     * @param buildingName - the value to which the name has to be set to
     */
    public void setBuilding_name(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     *
     * @return whether or not the building has non reservable space
     */
    public boolean isNon_reservable_space() {
        return nonReservableSpace;
    }

    /**
     * Sets the non reservable space to a given value
     * @param nonReservableSpace - the value the non reservable space needs to be changed into
     */
    public void setNon_reservable_space(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    /**
     * Shows whether there are parking spaces near a building.
     * @return whether or not there is a car parking space
     */
    public int getCar_parking_spaces() {
        return carParkingSpaces;
    }

    /**
     * Changes the value of the car parking space attribute.
     * @param carParkingSpaces - the value into which the attribute needs to be changed to
     */
    public void setCar_parking_spaces(int carParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
    }

    /**
     * Retrieves the description of a building.
     * @return the description of the building
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of the building.
     * @param description - what the building description should be changed into
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the time at which a building opens.
     * @return the opening time
     */
    public Time getOpening() {
        return opening;
    }

    /**
     * Sets the opening time to a given value.
     * @param opening - the value the opening time should be set to
     */
    public void setOpening(Time opening) {
        this.opening = opening;
    }

    /**
     * Retrieves the time at which a building closes.
     * @return the time at which the building closes
     */
    public Time getClosing() {
        return closing;
    }

    /**
     * Sets the closing time to a new given value.
     * @param closing - the value to which the closing time should be set
     */
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

    /**
     * This method sets the list of rooms to a given list.
     * @param rooms - the list to which the rooms'list needs to be set to
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Adds a room to the list of rooms a building has.
     * @param room - the room that needs to be added
     */
    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setBuilding(this);
    }

    /**
     * Removes a room from the list of rooms a building has.
     * @param room - the room that needs to be removed
     */
    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.setBuilding(null);
    }

    /**
     * A method that retrieves a list of all bikes.
     * @return the list of bikes parked near a building
     */
    public List<Bike> getBikes() {
        return bikes;
    }

    /**
     * adds a new list of bikes to a specific building.
     * @param bikes - the list of bikes that needs to be added
     */
    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    /**
     * Adds a bike to the list of bikes that can be reserved near a building.
     * @param bike - the bike that needs to be added
     */
    public void addBike(Bike bike) {
        this.bikes.add(bike);
        bike.setBuilding(this);
    }

    /**
     * Removes a bike from the list of bikes that can be reerved near a building.
     * @param bike - the bike that needs to be removed
     */
    public void removeBike(Bike bike) {
        this.bikes.remove(bike);
        bike.setBuilding(null);
    }

    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
        restaurant.setBuilding(this);
    }
    @JsonIgnore
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.setBuilding(null);

    }

}

