package nl.tudelft.oopp.entities;

import java.sql.Date;
import javax.persistence.*;


@Entity
@Table (name = "roomreservation")
public class RoomReservation {
    //getting the composite PK that uses two FK's to form the PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservationId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userFk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeslotFk;

    @Column(name = "day")
    private Date day;

    public RoomReservation() {
    }

    public RoomReservation(Date day) {
        this.day = day;
    }

    public int getReservation_id() {
        return reservationId;
    }

    public void setReservation_id(int reservationId) {
        this.reservationId = reservationId;
    }

    public User getUser_fk() {
        return userFk;
    }

    public void setUser_fk(User userFk) {
        this.userFk = userFk;
    }

    public TimeSlot getTimeslot_fk() {
        return timeslotFk;
    }

    public void setTimeslot_fk(TimeSlot timeslotFk) {
        this.timeslotFk = timeslotFk;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
