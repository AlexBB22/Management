package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.TimeSlot;
import nl.tudelft.oopp.entities.Type;



@Entity
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int room_id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "room_name")
    private String room_name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "building_name")
    private Building building;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<TimeSlot> timeslots = new ArrayList<TimeSlot>();

    public Room() {
    }

    public Room(int capacity, String room_name) {
        this.capacity = capacity;
        this.room_name = room_name;
    }


    public int getRoom_id() {
        return room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @JsonIgnore
    public List<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }
    
    public void addTimeslots(TimeSlot timeSlot) {
        this.timeslots.add(timeSlot);
    }

    public void removeTimeslots(TimeSlot timeSlot) {
        this.timeslots.remove(timeSlot);
    }

    public String toString() {
        return "room_id: " + this.room_id + ", room_name: " + this.room_name + ", capacity: " + this.capacity
                + ", building_name: " + this.getBuilding().getBuilding_Name() + ", type_id: " + this.getType().getType_id();
    }


}
