package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reservation")

public class Reservation {
    @Id
    @Column(name="reservation_id")
    private int reservationId;

    @Column(name="date")
    private String date;

    @ManyToOne
    @JoinColumn(name="id")
    private Student student;

    @ManyToMany(mappedBy = "reservations")
    private List<Room> rooms = new ArrayList<Room>();


}
