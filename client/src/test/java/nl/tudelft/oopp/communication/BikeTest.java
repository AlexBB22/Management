package nl.tudelft.oopp.communication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    private Bike b1;
    private Bike b2;
    private Building building;
    private BikeReservation br1;
    private BikeReservation br2;


    @BeforeEach
    void setUp() {
        Time t1 = new Time(1234);
        Time t2 = new Time(5678);
        Date d = new Date(20200331);
        building = new Building("Dw", true, 50, "Building for Examinations", t1, t2);
        br1 = new BikeReservation(d);
        br2 = new BikeReservation(d);
        List<BikeReservation> brList = new ArrayList<>();
        brList.add(br1);
        brList.add(br2);

        b2 = new Bike();
        b1 = new Bike(1, building,  brList);
    }

    @Test
    void constructorTest() {
        assertNotNull(b1);
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(b2);
    }


    @Test
    void getBikeId() {
        assertEquals(1, b1.getBikeId());
    }

    @Test
    void setBikeId() {
        b1.setBikeId(2);
        assertEquals(2, b1.getBikeId());
    }

    @Test
    void getBuilding() {
        assertEquals(building, b1.getBuilding());
    }

    @Test
    void setBuilding() {
        Time t1 = new Time(1234);
        Time t2 = new Time(5678);
        Building newBuilding = new Building("CSE", false, 50,
                "Building for Examinations", t1, t2);
        b1.setBuilding(newBuilding);
        assertEquals(newBuilding, b1.getBuilding());
    }

    @Test
    void getBikeReservations() {
        List<BikeReservation> test = new ArrayList<>();
        test.add(br1);
        test.add(br2);
        assertEquals(test, b1.getBikeReservations());
    }

    @Test
    void setBikeReservations() {
        List<BikeReservation> newList = new ArrayList<>();
        newList.add(br1);
        b1.setBikeReservations(newList);
        assertEquals(newList, b1.getBikeReservations());
    }
}