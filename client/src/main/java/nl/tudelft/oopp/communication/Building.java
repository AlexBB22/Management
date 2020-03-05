package nl.tudelft.oopp.communication;

import java.util.List;
import java.sql.Time;

public class Building {
    private boolean nonReservableSpace;
    private int carParkingSpaces;
    private String description;
    private Time opening;
    private Time closing;
    private String buildingName;
    private List<Room> rooms;
    private List<Restaurant> restaurants;
    private List<Bike> bikes;

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
    public Building(String buildingName, boolean nonReservableSpace, int carParkingSpaces, String description, Time opening, Time closing) {
        this.buildingName = buildingName;
        this.nonReservableSpace = nonReservableSpace;
        this.carParkingSpaces = carParkingSpaces;
        this.description = description;
        this.opening = opening;
        this.closing = closing;
    }

    public boolean isNon_reservable_space() {
        return nonReservableSpace;
    }

    public int getCar_parking_spaces() {
        return carParkingSpaces;
    }

    public String getDescription() {
        return description;
    }

    public Time getOpening() {
        return opening;
    }

    public  Time getClosing() {
        return closing;
    }

    public String getBuilding_Name() {
        return buildingName;
    }

    public void setNon_reservable_space(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    /**.
     * sets the amount of carparking amount to the amount given 0 if amount is smaller than 1
     * @param carParkingSpaces amount of spaces
     * @Author Scott Jochems
     */
    public void setCar_parking_spaces(int carParkingSpaces) {
        if (carParkingSpaces < 0) {
            this.carParkingSpaces = 0;
        } else {
            this.carParkingSpaces = carParkingSpaces;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpening(Time opening) {
        this.opening = opening;
    }

    public void setClosing(Time closing) {
        this.closing = closing;
    }

    public void setBuilding_Name(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }
}
