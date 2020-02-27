package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="building")
public class Building {

    @Id
    @Column(name="building_name")
    private String building_name;

    @Column(name="non-reservable space")
    private boolean non_reservable_space;

    @Column(name="car parking spaces")
    private int car_parking_spaces;

    @Column(name="description")
    private String description;

    @Column(name="opening_time")
    private Time opening;

    @Column (name="closing_time")
    private Time closing;

    @OneToMany(mappedBy = "building",  cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<Room>();


    public Building(){}

    public Building(String building_name, boolean non_reservable_space, int car_parking_spaces, String description,Time opening, Time closing){
        this.building_name=building_name;
        this.non_reservable_space=non_reservable_space;
        this.car_parking_spaces=car_parking_spaces;
        this.description=description;
        this.opening=opening;
        this.closing=closing;
    }

    public String getBuilding_Name() {
        return building_name;
    }
}

