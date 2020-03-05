package nl.tudelft.oopp.communication;

import java.util.List;

public class Building {
    private boolean nonReservableSpace;
    private int carParkingSpaces;
    private String description;
    private String opening;
    private String closing;
    private String buildingName;
    private List<Room> rooms;
    private List<Restaurant> restaurants;
    private List<Bike> bikes;

    public boolean isNon_reservable_space() {
        return nonReservableSpace;
    }

    public int getCar_parking_spaces() {
        return carParkingSpaces;
    }

    public String getDescription() {
        return description;
    }

    public String getOpening() {
        return opening;
    }

    public String getClosing() {
        return closing;
    }

    public String getBuilding_Name() {
        return buildingName;
    }

    public void setNon_reservable_space(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    public void setCar_parking_spaces(int carParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public void setClosing(String closing) {
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
