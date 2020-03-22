package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    @OneToMany(mappedBy = "bikeFk")
    private List<BikeReservation> bikeReservations = new ArrayList<BikeReservation>();

    public Bike(){
    }

    public int getBike_id() {
        return bikeId;
    }

    public void setBike_id(int bikeId) {
        this.bikeId = bikeId;
    }

    @JsonManagedReference(value = "bikeBikereservation")
    public List<BikeReservation> getBikeReservations() {
        return bikeReservations;
    }

    public void setBikeReservations(List<BikeReservation> bikeReservations) {
        this.bikeReservations = bikeReservations;
    }

    /**
     * Building getter.
     * @author Sartori Kendra
     * @return the building near which the bike is situated
     */
    @JsonIgnore
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
