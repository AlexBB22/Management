package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int timeslot_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name= "building_name")
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name= "room_id")
    private Room room;

    @Column(name="start_time")
    private Time start_time;

    @Column(name="end_time")
    private Time end_time;

    @OneToMany(mappedBy = "timeslot_fk", cascade = CascadeType.ALL)
    private List<RoomReservation> roomReservations = new ArrayList<RoomReservation>();

    public TimeSlot() {}
    public TimeSlot(Time start_time, Time end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(int timeslot_id) {
        this.timeslot_id = timeslot_id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Room getRoom() {
        return room;
    }

    @JsonManagedReference
    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "timeslot_id=" + timeslot_id +
                ", building_id=" + building.getBuilding_Name() +
                ", room=" + room.getRoom_name() +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }

    public void addRoomReservation(RoomReservation roomReservation) {
        this.roomReservations.add(roomReservation);
    }
    public void removeRoomReservation(RoomReservation roomReservation) {
        this.roomReservations.remove(roomReservation);
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o instanceof TimeSlot) {
            TimeSlot that = (TimeSlot) o;
            if (this.end_time.equals(that.end_time) && this.start_time.equals(that.start_time)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getStart_time());
    }
}
