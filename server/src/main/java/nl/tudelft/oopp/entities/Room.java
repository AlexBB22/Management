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
import nl.tudelft.oopp.entities.TimeSlot;
import nl.tudelft.oopp.entities.Type;



@Entity
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "room_name")
    private String roomName;

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

    public Room(int capacity, String roomName) {
        this.capacity = capacity;
        this.roomName = roomName;
    }


    public int getRoom_id() {
        return roomId;
    }

    public String getRoom_name() {
        return roomName;
    }

    public void setRoom_name(String roomName) {
        this.roomName = roomName;
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

    /**.
     * set capacity to given capacity if capacity is lower then zero then it is set to 0
     * @param capacity the capacity to set to
     * @Author Scott Jochems
     */
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            this.capacity = 0;
        } else {
            this.capacity = capacity;
        }
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
        return "room_id: " + this.roomId + ", room_name: " + this.roomName + ", capacity: " + this.capacity
                + ", building_name: " + this.getBuilding().getBuilding_Name() + ", type_id: " + this.getType().getType_id();
    }


}
