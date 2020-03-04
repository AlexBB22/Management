package nl.tudelft.oopp.entities;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name="bike_reservation")
public class BikeReservation {

    @Id
    @Column(name="bike_reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User bike_user_fk;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="bike_id")
    private Bike bike_fk;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "day")
    private Date day;

    public BikeReservation() {

    }

    public BikeReservation(Date day) {
        this.day = day;
    }



    /**
     * Retrieves the id of the bike reservation.
     * @return - the id of the reservation
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the infortmation about a user who reserved a bike.
     * @return - the user
     */
    public User getBike_user_fk() {
        return bike_user_fk;
    }

    /**
     * Sets the characteristics of the user to a given value.
     * @param bike_user_fk - the value the user should be set to
     */
    public void setBike_user_fk(User bike_user_fk) {
        this.bike_user_fk = bike_user_fk;
    }

    /**
     * Retrieves informatino about the bike that has been reserved.
     * @return the bike that has been reserved
     */
    public Bike getBike_fk() {
        return bike_fk;
    }

    /**
     * Sets the bike that has been reserved to a given value.
     * @param bike_fk - the value the bike should be set to
     */
    public void setBike_fk(Bike bike_fk) {
        this.bike_fk = bike_fk;
    }
}
