package nl.tudelft.oopp.demo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bike_reservation")
public class BikeReservation {

    @Id
    @Column(name="breservation_id")
    int id;


}
