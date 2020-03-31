package nl.tudelft.oopp.communication;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Bike {

    @JsonProperty("bike_id")
    private int bikeId;
    private Building building;
    private List<BikeReservation> bikeReservations;

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<BikeReservation> getBikeReservations() {
        return bikeReservations;
    }

    public void setBikeReservations(List<BikeReservation> bikeReservations) {
        this.bikeReservations = bikeReservations;
    }
}
