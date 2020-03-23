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

public class RoomTest {
    @Test
    public void roomConstructorTest() {
        Room room = new Room(8, "DW-01");
        assertNotNull(room);
    }

    @Test
    public void emptyRoomConstructorTest() {
        Room room = new Room();
        assertNotNull(room);
    }

    @Test
    public void getCapacityTest() {
        Room room = new Room(8, "DW-01");
        assertEquals(8, room.getCapacity());
    }

    @Test
    public void getRoomNameTest() {
        Room room = new Room(8, "DW-01");
        assertEquals("DW-01", room.getRoom_name());
    }

    @Test
    public void setCapacityTest() {
        Room room = new Room(8, "DW-01");
        room.setCapacity(4);
        assertEquals(4, room.getCapacity());
    }

    @Test
    public void setCapacityNegativeTest() {
        Room room = new Room(8, "DW-01");
        room.setCapacity(-10);
        assertEquals(0, room.getCapacity());
    }

    @Test
    public void setRoomNameTest() {
        Room room = new Room(8, "DW-01");
        room.setRoom_name("EWI-02");
        assertEquals("EWI-02", room.getRoom_name());
    }

    @Test
    public void getRoomIdTest() {
        Room room = new Room(8, "DW-01");
        assertEquals(0, room.getRoom_id());
    }

    @Test
    public void getRoomTypeTest() {
        Room room = new Room(8, "DW-01");
        assertNull(room.getType());
    }

    @Test
    public void setRoomTypeTest() {
        Room room = new Room(8, "DW-01");
        room.setType(new Type("lecture", true, true, true));
        assertNotNull(room.getType());
    }

    @Test
    public void getSetRoomTypeTest() {
        Room room = new Room(8, "DW-01");
        Type type = new Type("lecture", true, true, true);
        room.setType(type);
        assertEquals(type, room.getType());
    }

    @Test
    public void getBuildingTest() {
        Room room = new Room(8, "DW-01");
        assertNull(room.getBuilding());
    }

    @Test
    public void setBuildingTest() {
        Room room = new Room(8, "DW-01");
        Building building = new Building();
        room.setBuilding(building);
        assertNotNull(room.getBuilding());
    }

    @Test
    public void getSetBuildingTest() {
        Room room = new Room(8, "DW-01");
        Building building = new Building();
        room.setBuilding(building);
        assertEquals(building, room.getBuilding());
    }
}
