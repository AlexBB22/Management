package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        building.setBuilding_name(name);
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
    public void roomTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(0, building.getRooms().size());
        building.addRoom(new Room());
        assertEquals(1, building.getRooms().size());
        building.addRoom(new Room());
        Room room = new Room();
        building.addRoom(room);
        assertEquals(3, building.getRooms().size());
        building.removeRoom(room);
        assertEquals(2, building.getRooms().size());
    }

    @Test
    public void setRoomTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        Room room = new Room();
        Room room2 = new Room();
        building.addRoom(room);
        building.addRoom((room2));
        Room room3 = new Room();
        Room room4 = new Room();
        List<Room> list = new ArrayList<Room>();
        list.add(room3);
        list.add(room4);
        building.setRooms(list);
        assertEquals(list, building.getRooms());
    }


    @Test
    void getBikesTest() {

        Bike b1 = new Bike();
        b1.setBike_id(1);
        Bike b2 = new Bike();
        b2.setBike_id(2);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(b1);
        bikes.add(b2);
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        building.setBikes(bikes);

        assertEquals(bikes, building.getBikes());
    }

    @Test
    void setBikesTest() {
        Bike b1 = new Bike();
        b1.setBike_id(1);
        Bike b2 = new Bike();
        b2.setBike_id(2);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(b1);
        bikes.add(b2);
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        building.setBikes(bikes);

        List<Bike> newList = new ArrayList<>();
        newList.add(b1);
        building.setBikes(newList);

        assertEquals(newList, building.getBikes());
    }

    @Test
    void addBikesTest() {
        Bike b1 = new Bike();
        b1.setBike_id(1);
        Bike b2 = new Bike();
        b2.setBike_id(2);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(b1);
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        building.setBikes(bikes);
        building.addBike(b2);

        List<Bike> actual = new ArrayList<>();
        actual.add(b1);
        actual.add(b2);

        assertEquals(actual, building.getBikes());
    }


    @Test
    void removeBikesTest() {
        Bike b1 = new Bike();
        b1.setBike_id(1);
        Bike b2 = new Bike();
        b2.setBike_id(2);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(b1);
        bikes.add(b2);

        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        building.setBikes(bikes);
        building.removeBike(b2);

        List<Bike> actual = new ArrayList<>();
        actual.add(b1);
        assertEquals(actual, building.getBikes());
    }


    @Test
    void getRestaurantsTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Restaurant r1 = new Restaurant(1);
        Restaurant r2 = new Restaurant(2);


        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r1);
        restaurants.add(r2);

        building.setRestaurants(restaurants);
        assertEquals(restaurants, building.getRestaurants());
    }

    @Test
    void setRestaurantsTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Restaurant r1 = new Restaurant(1);
        Restaurant r2 = new Restaurant(2);


        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r1);
        restaurants.add(r2);

        building.setRestaurants(restaurants);

        Restaurant r3 = new Restaurant(3);
        restaurants.add(r3);
        building.setRestaurants(restaurants);

        assertEquals(restaurants, building.getRestaurants());

    }

    @Test
    void addRestaurantTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Restaurant r1 = new Restaurant(1);
        Restaurant r2 = new Restaurant(2);


        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r1);

        building.setRestaurants(restaurants);
        building.addRestaurant(r2);

        List<Restaurant> actual = new ArrayList<>();
        actual.add(r1);
        actual.add(r2);

        assertEquals(actual, building.getRestaurants());
    }

    @Test
    void removeRestaurantTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        Restaurant r1 = new Restaurant(1);
        Restaurant r2 = new Restaurant(2);

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r1);
        restaurants.add(r2);

        building.setRestaurants(restaurants);
        building.removeRestaurant(r2);

        List<Restaurant> actual = new ArrayList<>();
        actual.add(r1);

        assertEquals(actual, building.getRestaurants());
    }


    @Test
    void getBikeReservationsTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        BikeReservation br1 = new BikeReservation(new Date(20200401));
        BikeReservation br2 = new BikeReservation(new Date(20200403));
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        building.setBikeReservations(bikeReservations);
        assertEquals(bikeReservations, building.getBikeReservations());
    }

    @Test
    void setBikeReservationsTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        BikeReservation br1 = new BikeReservation(new Date(20200401));
        BikeReservation br2 = new BikeReservation(new Date(20200403));
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        building.setBikeReservations(bikeReservations);

        BikeReservation br3 = new BikeReservation(new Date(20200405));
        bikeReservations.add(br3);

        building.setBikeReservations(bikeReservations);

        assertEquals(bikeReservations, building.getBikeReservations());
    }

    @Test
    void addBikeReservationTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        BikeReservation br1 = new BikeReservation(new Date(20200401));
        BikeReservation br2 = new BikeReservation(new Date(20200403));
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);

        building.setBikeReservations(bikeReservations);
        building.addBikeReservation(br2);

        List<BikeReservation> actual = new ArrayList<>();
        actual.add(br1);
        actual.add((br2));

        assertEquals(actual, building.getBikeReservations());
    }

    @Test
    void removeBikeReservationTest() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        BikeReservation br1 = new BikeReservation(new Date(20200401));
        BikeReservation br2 = new BikeReservation(new Date(20200403));
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        building.setBikeReservations(bikeReservations);
        building.removeBikeReservation(br2);

        List<BikeReservation> actual = new ArrayList<>();
        actual.add(br1);

        assertEquals(actual, building.getBikeReservations());
    }

    @Test
    void equalsTest1() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);
        assertEquals(building, building);
    }

    @Test
    void equalsTest2() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        Building b = null;
        assertNotEquals(b, building);
    }

    @Test
    void equalsTest3() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        Room r = new Room();
        assertNotEquals(building, r);
    }

    @Test
    void equalsTest4() {
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        Building building2 = new Building("EWI", true, 300,
                "Tallest building on campus, has an elevator", time, time2);

        assertEquals(building, building2);
    }



}
