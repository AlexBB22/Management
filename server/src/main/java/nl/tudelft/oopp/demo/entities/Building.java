package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
@Entity
@Table(name = "building")
public class Building {

    @Id
    @Column(name = "name")
    private String buildingName;

    @Column(name = "non_reservable_space")
    private int nonResSpace;

    @Column(name = "car_parking_space")
    private int parkingSpace;

    @Column(name = "description")
    private String description;

    public Building() {}

    public Building(String buildingName, int nonResSpace, int parkingSpace, String description) {
        this.buildingName = buildingName;
        this.nonResSpace = nonResSpace;
        this.parkingSpace = parkingSpace;
        this.description = description;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getNonResSpace() {
        return nonResSpace;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public String getDescription() {
        return description;
    }
}
