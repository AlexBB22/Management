package nl.tudelft.oopp.communication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bike {

    @JsonProperty("bike_id")
    private int bikeId;
    private Building building;


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
}
