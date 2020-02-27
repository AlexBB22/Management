package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoomReservationCompositeID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "timeslot_fk", referencedColumnName = "timeslot_id")
    private TimeSlot timeslot;

    public RoomReservationCompositeID() {}

    public RoomReservationCompositeID(User user, TimeSlot timeslot) {
        this.user = user;
        this.timeslot = timeslot;
    }

    public User getUser() {
        return this.user;
    }

    public TimeSlot getTimeSlot() {
        return this.timeslot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof RoomReservationCompositeID) {
            RoomReservationCompositeID that = (RoomReservationCompositeID) o;
            return Objects.equals(getUser(), that.getUser()) && Objects.equals(getTimeSlot(), that.getTimeSlot());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getTimeSlot());
    }
}
