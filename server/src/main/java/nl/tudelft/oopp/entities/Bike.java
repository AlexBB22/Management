package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @OneToMany(mappedBy = "bikeFk")
    private List<BikeReservation> bikeReservations = new ArrayList<BikeReservation>();

    public Bike(){
    }

    /**
     * Getter for the bike id.
     * @return - the bike id
     */
    public int getBike_id() {
        return bikeId;
    }

    /**
     * setter for the bike id.
     * @param bikeId - Sets the value of the bike to this parameter
     */
    public void setBike_id(int bikeId) {
        this.bikeId = bikeId;
    }

    /**.
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
