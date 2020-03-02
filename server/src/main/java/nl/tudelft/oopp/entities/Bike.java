package nl.tudelft.oopp.entities;

import nl.tudelft.oopp.entities.Building;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bike")
public class Bike {

    @Id
    @Column(name = "bike_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int bike_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_name")
    private Building building;

    @OneToMany(mappedBy = "bike_fk")
    private List<BikeReservation> bikeReservations = new ArrayList<BikeReservation>();

    public Bike(){}

    public int getBike_id() {
        return bike_id;
    }

    public void setBike_id(int bike_id) {
        this.bike_id = bike_id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
