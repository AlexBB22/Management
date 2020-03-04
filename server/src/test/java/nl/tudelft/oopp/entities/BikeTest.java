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
}
