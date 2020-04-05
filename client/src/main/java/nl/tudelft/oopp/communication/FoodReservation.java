package nl.tudelft.oopp.communication;

import java.sql.Date;
import java.sql.Time;

public class FoodReservation {
    private int reservationId;
    private User userFk;
    private Food foodFk;
    private Restaurant restaurantFk;
    private Date day;
    private Time startTime;
    private Time endTime;

    public FoodReservation() {
    }

    public FoodReservation(Date day) {
        this.day = day;
    }

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
}
