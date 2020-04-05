package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
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
@Table (name = "foodreservation")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservationId")
public class FoodReservation { //implements Serializable {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservationId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User userFk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "food")
    private Food foodFk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurantFk;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "day")
    private Date day;

    @JoinColumn(name = "start_time")
    private Time startTime;

    @JoinColumn(name = "end_time")
    private Time endTime;

    public FoodReservation() {

    }

    public FoodReservation(Date day) {
        this.day = day;
    }

    //@JsonManagedReference(value = "reservation_id")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public User getUserFk() {
        return userFk;
    }

    public void setUserFk(User userFk) {
        this.userFk = userFk;
    }

    public Food getFoodFk() {
        return foodFk;
    }

    public void setFoodFk(Food foodFk) {
        this.foodFk = foodFk;
    }

    public Restaurant getRestaurantFk() {
        return restaurantFk;
    }

    public void setRestaurantFk(Restaurant restaurantFk) {
        this.restaurantFk = restaurantFk;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * The representation of a food reservation as a string.
     * @author - Niels Tomassen
     * @return - a string containing information about a food reservation
     */
    public String toString() {
        return "Food order unique ID: "
                + this.reservationId + ", Food: " + this.foodFk.getName()
                + ", Restaurant: " + this.restaurantFk.getRestaurantName() + ",\nDay: "
                + this.day + ", Start Time: "
                + this.startTime + ", End Time: " + this.endTime;
    }
}
