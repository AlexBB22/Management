package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import nl.tudelft.oopp.entities.Room;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int timeslotId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "building_name")
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @OneToMany(mappedBy = "timeslotFk", cascade = CascadeType.ALL)
    private List<RoomReservation> roomReservations = new ArrayList<RoomReservation>();

    public TimeSlot() {
    }

    public TimeSlot(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTimeslot_id() {
        return timeslotId;
    }

    public void setTimeslot_id(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    @JsonBackReference(value = "timeslotBuilding")
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @JsonBackReference(value = "timeslotRoom")
    public Room getRoom() {
        return room;
    }

    @JsonManagedReference(value = "timeslotRoomReservations")
    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    @Override
    public String toString() {
        return "TimeSlot{"
                + "timeslot_id=" + timeslotId
                + ", building_id=" + building.getBuilding_Name()
                + ", room=" + room.getRoom_name()
                + ", start_time=" + startTime
                + ", end_time=" + endTime
                + '}';
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
        return startTime;
    }

    public void setStart_time(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEnd_time() {
        return endTime;
    }

    public void setEnd_time(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof TimeSlot) {
            TimeSlot that = (TimeSlot) o;
            if (this.endTime.equals(that.endTime) && this.startTime.equals(that.startTime)) {
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
