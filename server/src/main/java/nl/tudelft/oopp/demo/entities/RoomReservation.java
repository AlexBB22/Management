package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table (name = "roomreservation")
public class RoomReservation {
    //getting the composite PK that uses two FK's to form the PK
    @Id
    private int reservation_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user_fk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeslot_fk;

    @Column(name="day")
    private String day;

    public RoomReservation() {}
    public RoomReservation(User user_fk, TimeSlot timeslot_fk, String day) {
        this.user_fk = user_fk;
        this.timeslot_fk = timeslot_fk;
        this.day = day;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public User getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(User user_fk) {
        this.user_fk = user_fk;
    }

    public TimeSlot getTimeslot_fk() {
        return timeslot_fk;
    }

    public void setTimeslot_fk(TimeSlot timeslot_fk) {
        this.timeslot_fk = timeslot_fk;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
