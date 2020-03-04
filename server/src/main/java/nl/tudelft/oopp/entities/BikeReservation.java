package nl.tudelft.oopp.entities;

import java.sql.Date;
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

@Entity
@Table(name = "bike_reservation")
public class BikeReservation {

    @Id
    @Column(name = "bike_reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User bikeUserFk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bike_id")
    private Bike bikeFk;

    @Column(name = "day")
    private Date day;

    public BikeReservation() {
    }

    public int getId() {
        return id;
    }

    public User getBike_user_fk() {
        return bikeUserFk;
    }

    public void setBike_user_fk(User bikeUserFk) {
        this.bikeUserFk = bikeUserFk;
    }

    public Bike getBike_fk() {
        return bikeFk;
    }

    public void setBike_fk(Bike bikeFk) {
        this.bikeFk = bikeFk;
    }
}
