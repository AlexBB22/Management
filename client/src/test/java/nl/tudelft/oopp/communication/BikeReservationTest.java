package nl.tudelft.oopp.communication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BikeReservationTest {

    private BikeReservation br1;
    private BikeReservation br2;
    private Bike bike;
    private User user;
    private Date date;
    private Building building;

    @BeforeEach
    void setUp() {
        Time t1 = new Time(1234);
        Time t2 = new Time(5678);
        Date d1 = new Date(20200331);
        Date d2 = new Date(20200401);
        Date d3 = new Date(20200402);
        date = new Date(20200331);

        building = new Building("DW", true, 50, "Building for Examinations", t1, t2);
        BikeReservation bikeReservation1 = new BikeReservation(d1);
        BikeReservation bikeReservation2 = new BikeReservation(d2);
        List<BikeReservation> brList = new ArrayList<>();
        brList.add(bikeReservation1);
        brList.add(bikeReservation2);

        bike = new Bike(1, building,  brList);

        Role role = new Role(2, "Student");
        user = new User(15, "123@test", "Kanish", "password", role);

        br1 = new BikeReservation(date);
        br2 = new BikeReservation();

        br1.setId(1);
        br1.setBike_fk(bike);
        br1.setBike_user_fk(user);
        br1.setBuilding(building);

    }

    @Test
    void constructorTest() {
        assertNotNull(br1);
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(br2);
    }

    @Test
    void getId() {
        assertEquals(1, br1.getId());
    }

    @Test
    void setId() {
        br1.setId(2);
        assertEquals(2, br1.getId());
    }

    @Test
    void getBike_user_fk() {
        assertEquals(user, br1.getBike_user_fk());
    }

    @Test
    void setBike_user_fk() {
        Role role = new Role(2, "Student");
        User user2 = new User(15, "123@test", "Test", "pass", role);
        br1.setBike_user_fk(user2);
        assertEquals(user2, br1.getBike_user_fk());
    }

    @Test
    void getBike_fk() {
        assertEquals(bike, br1.getBike_fk());
    }

    @Test
    void setBike_fk() {
        Date d1 = new Date(20200331);
        Date d2 = new Date(20200404);
        Time t1 = new Time(1234);
        Time t2 = new Time(5678);
        Building building = new Building("Dw", true, 50, "Building for Examinations", t1, t2);
        BikeReservation bikeReservation1 = new BikeReservation(d1);
        BikeReservation bikeReservation2 = new BikeReservation(d2);
        List<BikeReservation> brList = new ArrayList<>();
        brList.add(bikeReservation1);
        brList.add(bikeReservation2);

        Bike bike2 = new Bike(1, building,  brList);
        br1.setBike_fk(bike2);
        assertEquals(bike2, br1.getBike_fk());
    }

    @Test
    void getDay() {
        assertEquals(date, br1.getDay());
    }

    @Test
    void setDay() {
        Date d3 = new Date(20200402);
        br1.setDay(d3);
        assertEquals(d3, br1.getDay());
    }

    @Test
    void getBuilding() {
        assertEquals(building, br1.getBuilding());
    }

    @Test
    void setBuilding() {
        Time t1 = new Time(1234);
        Time t2 = new Time(5678);
        Building temp = new Building("CSE", false, 50, "Building for lectures", t1, t2);
        br1.setBuilding(temp);
        assertEquals(temp, br1.getBuilding());
    }

    @Test
    void testToString() {
        String res = "Bike reservation unique ID: 1, day: 1970-01-01, location: DW";
        assertEquals(res, br1.toString());
    }
}