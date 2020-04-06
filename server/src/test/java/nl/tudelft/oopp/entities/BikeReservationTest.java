package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

public class BikeReservationTest {

    @Test
    public void bikeReservationConstructorTest() {
        assertNotNull(new BikeReservation());
    }


    @Test
    public void constructorTest() {
        Date d = new Date(20200401);
        BikeReservation br = new BikeReservation(d);
        assertNotNull(br);
    }


    @Test
    public void getBikeReservationId() {
        BikeReservation br1 = new BikeReservation();
        assertEquals(0, br1.getId());
    }

    @Test
    public void setBikeReservationID() {
        BikeReservation br1 = new BikeReservation();
        br1.setId(5);
        assertEquals(5, br1.getId());
    }

    @Test
    public void getBikeReservationUserId() {
        BikeReservation br1 = new BikeReservation();
        assertNull(br1.getBike_user_fk());
    }

    @Test
    public void setBikeReservationUserId() {
        BikeReservation br1 = new BikeReservation();
        User anton = new User(0, "a", "b", "c");
        br1.setBike_user_fk(anton);
        assertEquals(anton, br1.getBike_user_fk());
    }

    @Test
    public void getBikeFk() {
        BikeReservation b1 = new BikeReservation();
        assertNull(b1.getBike_fk());
    }

    @Test
    public void setBikeFk() {
        BikeReservation br1 = new BikeReservation();
        Bike b = new Bike();
        br1.setBike_fk(b);
        assertEquals(b, br1.getBike_fk());
    }

    @Test
    public void setBikeFkNotNull() {
        BikeReservation br1 = new BikeReservation();
        Bike b = new Bike();
        br1.setBike_fk(b);
        assertNotNull(br1.getBike_fk());
    }

    @Test
    public void setUserIdNotNull() {
        BikeReservation br1 = new BikeReservation();
        User anton = new User(0, "a", "b", "c");
        br1.setBike_user_fk(anton);
        assertNotNull(br1.getBike_user_fk());
    }

    @Test
    void getDay() {
        Date d = new Date(20200401);
        BikeReservation br = new BikeReservation(d);
        assertEquals(d, br.getDay());
    }

    @Test
    void setDay() {
        Date d = new Date(20200401);
        BikeReservation br = new BikeReservation(d);
        Date d2 = new Date(20200403);
        br.setDay(d2);

        assertEquals(d2, br.getDay());
    }

    @Test
    void getBuilding() {
        Date d = new Date(20200401);
        BikeReservation br = new BikeReservation(d);
        Time st = new Time(135134);
        Time et = new Time(145141);
        Building b = new Building("DW", true, 50, "Nice place", st, et);

        br.setBuilding(b);
        assertEquals(b, br.getBuilding());
    }

    @Test
    void setBuilding() {
        Date d = new Date(20200401);
        BikeReservation br = new BikeReservation(d);
        Time st = new Time(135134);
        Time et = new Time(145141);
        Building b = new Building("DW", true, 50, "Nice place", st, et);
        br.setBuilding(b);

        Building actual = new Building("CSE", false, 50, "Ok place", st, et);
        br.setBuilding(actual);
        assertEquals(actual, br.getBuilding());
    }

    @Test
    void toStringTest() {
        Date d = new Date(20200401);
        BikeReservation br = new BikeReservation(d);
        Time st = new Time(135134);
        Time et = new Time(145141);
        Building b = new Building("DW", true, 50, "Nice place", st, et);
        br.setBuilding(b);

        String res = "Bike reservation unique ID: 0, day: 1970-01-01, location: DW";
        assertEquals(res, br.toString());
    }
}
