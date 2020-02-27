package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table(name="bike")
public class Bike {

    @Id
    @Column(name="bike_id")
    int bike_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="building_name")
    private Building building;

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
