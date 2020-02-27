package nl.tudelft.oopp.demo.entities;


import javax.persistence.*;

@Entity
@Table(name="bike_reservation")
public class BikeReservation {

    @Id
    @Column(name="bike_reservation_id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User bike_user_fk;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="bike_id")
    private Bike bike_fk;

    public BikeReservation() {}
    public BikeReservation(User bike_user_fk, Bike bike_fk) {
        this.bike_user_fk = bike_user_fk;
        this.bike_fk=bike_fk;
    }

    public int getId() {
        return id;
    }

    public User getBike_user_fk() {
        return bike_user_fk;
    }

    public void setBike_user_fk(User bike_user_fk) {
        this.bike_user_fk = bike_user_fk;
    }

    public Bike getBike_fk() {
        return bike_fk;
    }

    public void setBike_fk(Bike bike_fk) {
        this.bike_fk = bike_fk;
    }
}
