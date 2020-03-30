package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void typeConstructor() {
        assertNotNull(new Type("someRoom", true, true, true));
    }

    @Test
    public void getTypeId() {
        Type type = new Type("someRoom", true, true, true);
        assertEquals(0, type.getType_id());
    }

    @Test
    public void getSetTV() {
        Type type = new Type("someRoom", true, true, true);
        type.setTv(true);
        assertEquals(true, type.isTv());
    }

    @Test
    public void getSetPowerOutlets() {
        Type type = new Type("someRoom", true, true, true);
        type.setPowerOutlets(false);
        assertEquals(false, type.isPowerOutlets());
    }

    @Test
    public void getSetWhiteBoard() {
        Type type = new Type("someRoom", true, true, true);
        type.setWhiteBoard(false);
        assertEquals(false, type.isWhiteBoard());
    }

    @Test
    public void getSetClicker() {
        Type type = new Type("someRoom", true, true, true);
        type.setClicker(false);
        assertEquals(false, type.isClicker());
    }

    @Test
    public void getSetName() {
        Type type = new Type("someRoom", true, true, true);
        type.setName("someOtherRoom");
        assertEquals("someOtherRoom", type.getName());
    }

    @Test
    public void getSetListRooms() {
        Type type = new Type("someRoom", true, true, true);
        List<Room> roomList = new ArrayList<Room>();
        type.setListOfRooms(roomList);
        assertEquals(roomList, type.getListOfRooms());
    }

    @Test
    public void addRemoveRoom() {
        Type type = new Type("someRoom", true, true, true);
        List<Room> roomList = new ArrayList<Room>();
        type.setListOfRooms(roomList);
        Room room = new Room(10, "smexy");
        type.addRoom(room);
        assertEquals(room, type.getListOfRooms().get(0));
        type.removeRoom(room);
        assertEquals(0, type.getListOfRooms().size());
    }

    @Test
    public void toStringTest() {
        Type type = new Type("someRoom", true, true, true);
        String toString = "type_id: " + type.getType_id() + ", name: " + type.getName() + ", clicker: " + type.isClicker() + ", tv: " + type.isTv()
                + ", power_outlets: " + type.isPowerOutlets() + ", whiteboard: " + type.isWhiteBoard();
        assertEquals(toString, type.toString());
    }
}
