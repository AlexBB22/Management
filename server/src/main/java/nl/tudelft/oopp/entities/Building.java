package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    @OneToMany(mappedBy = "building",  cascade = CascadeType.ALL)
    private List<BikeReservation> bikeReservations = new ArrayList<BikeReservation>();

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
     * @author Scott Jochems
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
     * Building name getter.
     * @return the name of the building
     */
    public String getBuilding_Name() {
        return buildingName;
    }

    /**
     * Sets the name of a building to a given value.
     * @param buildingName - the value to which the name has to be set to
     */
    public void setBuilding_name(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * IsNon_reservable_space getter.
     * @return whether or not the building has non reservable space
     */
    public boolean isNon_reservable_space() {
        return nonReservableSpace;
    }

    /**
     * Sets the non reservable space to a given value.
     * @param nonReservableSpace - the value the non reservable space needs to be changed into
     */
    public void setNon_reservable_space(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    public int getCar_parking_spaces() {
        return carParkingSpaces;
    }

    /**.
     *sets the amount of car parking space to given integer, 0 if given integer is below 0
     * @param carParkingSpaces amount the car parking space to set to
     * @author Scott Jochems
     */
    public void setCar_parking_spaces(int carParkingSpaces) {
        if (carParkingSpaces < 0) {
            this.carParkingSpaces = 0;
        } else {
            this.carParkingSpaces = carParkingSpaces;
        }
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


    /**
     * Rooms getter.
     * @return list of rooms
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

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public void addBike(Bike bike) {
        this.bikes.add(bike);
        bike.setBuilding(this);
    }

    public void removeBike(Bike bike) {
        this.bikes.remove(bike);
        bike.setBuilding(null);
    }

    public void addRestaurant(Restaurant restaurant) {
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

    /**
     * remove a restaurant from the building.
     * @param restaurant restaurant to be deleted
     * @author Scott Jochems
     */
    public void removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.setBuilding(null);
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public boolean isNonReservableSpace() {
        return nonReservableSpace;
    }

    public void setNonReservableSpace(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    public int getCarParkingSpaces() {
        return carParkingSpaces;
    }

    public void setCarParkingSpaces(int carParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
    }

    @JsonIgnore
    public List<BikeReservation> getBikeReservations() {
        return bikeReservations;
    }

    public void setBikeReservations(List<BikeReservation> bikeReservations) {
        this.bikeReservations = bikeReservations;
    }

    public void addBikeReservation(BikeReservation reservation) {
        this.bikeReservations.add(reservation);
        reservation.setBuilding(this);
    }

    public void removeBikeReservation(BikeReservation reservation) {
        this.bikeReservations.remove(reservation);
        reservation.setBuilding(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Building building = (Building) o;
        return Objects.equals(buildingName, building.buildingName);
    }

}

