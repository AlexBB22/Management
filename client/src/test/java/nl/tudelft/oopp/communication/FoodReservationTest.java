package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class FoodReservationTest {
    private FoodReservation fr1;
    private FoodReservation fr2;
    private User u1;
    private Food f1;
    private Restaurant r1;
    private Date d1;
    private Time st;
    private Time et;

    @BeforeEach
    void setUp() {
        Role role = new Role(2, "Staff");
        u1 = new User(1, "123@test", "Kanish", "password", role);
        f1 = new Food("Burger", 10);
        r1 = new Restaurant(2, "Starbucks");
        d1 = new Date(20200405);
        st = new Time(1234);
        et = new Time(5678);
        fr1 = new FoodReservation(d1);
        fr2 = new FoodReservation();
        fr1.setFoodFk(f1);
        fr1.setEndTime(et);
        fr1.setStartTime(st);
        fr1.setUserFk(u1);
        fr1.setReservationId(5);
        fr1.setRestaurantFk(r1);
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(fr2);
    }

    @Test
    void constructorTest() {
        assertNotNull(fr1);
    }


    @Test
    void getReservationId() {
        assertEquals(5, fr1.getReservationId());
    }

    @Test
    void setReservationId() {
        fr1.setReservationId(10);
        assertEquals(10, fr1.getReservationId());
    }

    @Test
    void getUserFk() {
        assertEquals(u1, fr1.getUserFk());
    }

    @Test
    void setUserFk() {
        Role role = new Role(3, "Teacher");
        User u2 = new User(9, "456@test", "Kanish2", "pass", role);
        fr1.setUserFk(u2);
        assertEquals(u2, fr1.getUserFk());
    }

    @Test
    void getFoodFk() {
        assertEquals(f1, fr1.getFoodFk());
    }

    @Test
    void setFoodFk() {
        Food f2 = new Food("Fries", 3);
        fr1.setFoodFk(f2);
        assertEquals(f2, fr1.getFoodFk());
    }

    @Test
    void getRestaurantFk() {
        assertEquals(r1, fr1.getRestaurantFk());
    }

    @Test
    void setRestaurantFk() {
        Restaurant r2 = new Restaurant(5, "McDonalds");
        fr1.setRestaurantFk(r2);
        assertEquals(r2, fr1.getRestaurantFk());
    }

    @Test
    void getDay() {
        assertEquals(d1, fr1.getDay());
    }

    @Test
    void setDay() {
        Date d2 = new Date(20200406);
        fr1.setDay(d2);
        assertEquals(d2, fr1.getDay());
    }

    @Test
    void getStartTime() {
        assertEquals(st, fr1.getStartTime());
    }

    @Test
    void setStartTime() {
        Time st2 = new Time(14567);
        fr1.setStartTime(st2);
        assertEquals(st2, fr1.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(et, fr1.getEndTime());
    }

    @Test
    void setEndTime() {
        Time et2 = new Time(67876);
        fr1.setEndTime(et2);
        assertEquals(et2, fr1.getEndTime());
    }
}