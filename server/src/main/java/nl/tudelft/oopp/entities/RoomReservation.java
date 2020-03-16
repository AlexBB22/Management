package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "roomreservation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservation_id")
public class RoomReservation implements Serializable {
    //getting the composite PK that uses two FK's to form the PK
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservationId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userFk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    @JsonBackReference(value = "userRoomReservations")
    public User getUser_fk() {
        return userFk;
    }

    public void setUser_fk(User userFk) {
        this.userFk = userFk;
    }


    @JsonBackReference(value = "timeslotRoomReservations")
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
