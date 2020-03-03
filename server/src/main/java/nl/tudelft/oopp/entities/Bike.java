package nl.tudelft.oopp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import nl.tudelft.oopp.entities.Building;

@Entity
@Table(name = "bike")
public class Bike {

    @Id
    @Column(name = "bike_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int bikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_name")
    private Building building;

    @OneToMany(mappedBy = "bike_fk")
    private List<BikeReservation> bikeReservations = new ArrayList<BikeReservation>();

    public Bike(){
    }

    public int getBike_id() {
        return bikeId;
    }

    public void setBike_id(int bikeId) {
        this.bikeId = bikeId;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
