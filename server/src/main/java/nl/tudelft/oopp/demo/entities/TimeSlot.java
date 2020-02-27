package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    private int timeslot_id;

    @Column
    private int start_time;

    @Column
    private int end_time;

    public TimeSlot() {}
    public TimeSlot(int timeSlot_id, int start_time, int end_time) {
        this.timeslot_id = timeSlot_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }


    public int getTimeSlot_id() {
        return timeslot_id;
    }

    public void setTimeSlot_id(int timeSlot_id) {
        this.timeslot_id = timeSlot_id;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o instanceof TimeSlot) {
            TimeSlot that = (TimeSlot) o;
            if (this.end_time == that.end_time && this.start_time == that.start_time) {
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
