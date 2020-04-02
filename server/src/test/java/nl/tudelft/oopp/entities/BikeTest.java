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

public class BikeTest {

    @Test
    public void bikeConstructorTest() {
        Bike bike = new Bike();
        assertNotNull(bike);
    }

    @Test
    public void getBikeIdTest() {
        Bike bike = new Bike();
        assertEquals(0, bike.getBike_id());
    }

    @Test
    public void setBikeIdTest() {
        Bike bike = new Bike();
        bike.setBike_id(2);
        assertEquals(2, bike.getBike_id());
    }

    @Test
    public void getBuildingTest() {
        Bike bike = new Bike();
        assertNull(bike.getBuilding());
    }

    @Test
    public void setBuildingTest() {
        Bike bike = new Bike();
        bike.setBuilding(new Building());
        assertNotNull(bike.getBuilding());
    }

    @Test
    public void getAndSetBuildingTest() {
        Bike bike = new Bike();
        Building building = new Building();
        bike.setBuilding(building);
        assertEquals(building, bike.getBuilding());
    }

    @Test
    void getBikeReservations() {
        Bike bike = new Bike();
        Date d1 = new Date(20200401);
        Date d2 = new Date(20200403);
        BikeReservation br1 = new BikeReservation(d1);
        BikeReservation br2 = new BikeReservation(d2);

        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        bike.setBikeReservations(bikeReservations);
        assertEquals(bikeReservations, bike.getBikeReservations());
    }


    @Test
    void setBikeReservations() {
        Bike bike = new Bike();
        Date d3 = new Date(20200404);
        Date d4 = new Date(20200405);
        BikeReservation br1 = new BikeReservation(d3);
        BikeReservation br2 = new BikeReservation(d4);

        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        bike.setBikeReservations(bikeReservations);
        assertEquals(bikeReservations, bike.getBikeReservations());
    }

}
