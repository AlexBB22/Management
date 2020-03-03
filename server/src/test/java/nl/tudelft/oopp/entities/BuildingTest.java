package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {

    @Test
    public void buildingConstructorTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0 );
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertNotNull(building);
    }

    @Test
    public void emptyBuildingConstructorTest(){
        Building building = new Building();
        assertNotNull(building);
    }

    @Test
    public void getNameTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0 );
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
    public void getCarParkingSpacesTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(300, building.getCar_parking_spaces());
    }

    @Test
    public void getDescriptionTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals("Tallest building on campus, has an elevator", building.getDescription());
    }

    @Test
    public void getOpeningTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(time, building.getOpening());
    }

    @Test
    public void getClosingTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        assertEquals(time2, building.getClosing());
    }

    @Test
    public void setNameTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        String name = "DW";
        building.setBuilding_name(name);
        assertEquals(name, building.getBuilding_Name());
    }

    @Test
    public void setNonResSpaceTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        building.setNon_reservable_space(false);
        assertFalse(building.isNon_reservable_space());
    }

    @Test
    public void setCarParkingSpacesTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        building.setCar_parking_spaces(5);
        assertEquals(5, building.getCar_parking_spaces());
    }

    @Test
    public void setCarParkingSpacesNegativeTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        building.setCar_parking_spaces(-10);
        assertEquals(0, building.getCar_parking_spaces());
    }

    @Test
    public void setDescriptionTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        String description = "Tallest building on campus, has multiple elevators";
        building.setDescription(description);
        assertEquals(description, building.getDescription());
    }

    @Test
    public void setOpeningTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        Time time3 = new Time(5,45,0);
        building.setOpening(time3);
        assertEquals(time3, building.getOpening());
    }

    @Test
    public void setClosingTest(){
        Time time = new Time(6, 45, 0);
        Time time2 = new Time(21, 0, 0);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        Time time3 = new Time(22, 30, 0);
        building.setClosing(time3);
        assertEquals(time3, building.getClosing());
    }

    @Test
    public void RoomTest(){
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
    public void setRoomTest(){
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
}
