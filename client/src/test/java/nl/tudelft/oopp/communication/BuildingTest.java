package nl.tudelft.oopp.communication;

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

public class BuildingTest {

    @Test
    public void buildingConstructorTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertNotNull(building);
    }

    @Test
    public void emptyBuildingConstructorTest() {
        Building building = new Building();
        assertNotNull(building);
    }

    @Test
    public void getNameTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals("EWI", building.getBuilding_Name());
    }

    @Test
    public void hasNonResSpaceTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", false, 300, "Tallest building on campus, has an elevator", time, time2);
        assertFalse(building.isNon_reservable_space());
    }

    @Test
    public void hasNonResSpaceTestTrue() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertTrue(building.isNon_reservable_space());
    }

    @Test
    public void getCarParkingSpacesTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(300, building.getCar_parking_spaces());
    }

    @Test
    public void getDescriptionTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals("Tallest building on campus, has an elevator", building.getDescription());
    }

    @Test
    public void getOpeningTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(time, building.getOpening());
    }

    @Test
    public void getClosingTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(time2, building.getClosing());
    }

    @Test
    public void setNameTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        String name = "DW";
        building.setBuilding_Name(name);
        assertEquals(name, building.getBuilding_Name());
    }

    @Test
    public void setNonResSpaceTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        building.setNon_reservable_space(false);
        assertFalse(building.isNon_reservable_space());
    }

    @Test
    public void setCarParkingSpacesTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        building.setCar_parking_spaces(5);
        assertEquals(5, building.getCar_parking_spaces());
    }

    @Test
    public void setCarParkingSpacesNegativeTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        building.setCar_parking_spaces(-10);
        assertEquals(0, building.getCar_parking_spaces());
    }

    @Test
    public void setDescriptionTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        String description = "Tallest building on campus, has multiple elevators";
        building.setDescription(description);
        assertEquals(description, building.getDescription());
    }

    @Test
    public void setOpeningTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        Time time3 = new Time(5,45,0);
        building.setOpening(time3);
        assertEquals(time3, building.getOpening());
    }

    @Test
    public void setClosingTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        Time time3 = new Time(22, 30, 0);
        building.setClosing(time3);
        assertEquals(time3, building.getClosing());
    }

    @Test
    void toStringTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);

        String res = "Building{nonReservableSpace=true, carParkingSpaces=300, "
                + "description='Tallest building on campus, has an elevator', opening=06:45:00, closing=21:00:00, "
                + "buildingName='EWI'}";
        assertEquals(res, building.toString());
    }

    @Test
    void testGetRooms() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Room r1 = new Room(50, "IZ4");
        Room r2 = new Room(100, "IZ3");
        List<Room> roomList = new ArrayList<>();
        roomList.add(r1);
        roomList.add(r2);
        building.setRooms(roomList);
        assertEquals(roomList, building.getRooms());
    }

    @Test
    void setRooms() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Room r3 = new Room(50, "IZ2");
        Room r4 = new Room(100, "IZ1");
        List<Room> roomList = new ArrayList<>();
        roomList.add(r3);
        roomList.add(r4);
        building.setRooms(roomList);
        assertEquals(roomList, building.getRooms());
    }

    @Test
    void testGetRestaurants() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Restaurant r1 = new Restaurant(1, "KFC");
        Restaurant r2 = new Restaurant(2, "Burger King");
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r1);
        restaurants.add(r2);
        building.setRestaurants(restaurants);
        assertEquals(restaurants, building.getRestaurants());
    }

    @Test
    void testSetRestaurants() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Restaurant r3 = new Restaurant(3, "KFC");
        Restaurant r4 = new Restaurant(4, "Burger King");
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r3);
        restaurants.add(r4);
        building.setRestaurants(restaurants);
        assertEquals(restaurants, building.getRestaurants());
    }

    @Test
    void testGetBikes() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        List<BikeReservation> reservations = new ArrayList<>();
        Bike b1 = new Bike(1, building, reservations);
        Bike b2 = new Bike(2, building, reservations);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(b1);
        bikes.add(b2);
        building.setBikes(bikes);
        assertEquals(bikes, building.getBikes());

    }

    @Test
    void testSetBikes() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        List<BikeReservation> reservations = new ArrayList<>();
        Bike b3 = new Bike(3, building, reservations);
        Bike b4 = new Bike(4, building, reservations);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(b3);
        bikes.add(b4);
        building.setBikes(bikes);
        assertEquals(bikes, building.getBikes());
    }


}
