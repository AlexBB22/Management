package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AvailableRoomTest {

    private AvailableRoom ar1;

    @BeforeEach
    void setUp() {
        ar1 = new AvailableRoom("CSE", "Ampere", "LectureHall", 1,
                150, true, true, true, true);
    }

    @Test
    void testConstructor() {
        assertNotNull(ar1);
    }

    @Test
    void emptyConstructorTest() {
        AvailableRoom ar2 = new AvailableRoom();
        assertNotNull(ar2);
    }

    @Test
    void testToString() {
        String res = "AvailableRoom{buildingName='CSE', roomName='Ampere', name='LectureHall', roomID=1, capacity=150, clicker=true, powerOutlets=true, "
                +
                "tv=true, whiteboard=true}";
        assertEquals(res, ar1.toString());
    }

    @Test
    void getBuildingName() {
        assertEquals("CSE", ar1.getBuildingName());
    }


    @Test
    void setBuildingName() {
        String newName = "DW";
        ar1.setBuildingName(newName);
        assertEquals("DW", ar1.getBuildingName());
    }

    @Test
    void getRoomName() {
        assertEquals("Ampere", ar1.getRoomName());
    }

    @Test
    void setRoomName() {
        ar1.setRoomName("Pi");
        assertEquals("Pi", ar1.getRoomName());
    }

    @Test
    void getName() {
        assertEquals("LectureHall", ar1.getName());
    }

    @Test
    void setName() {
        ar1.setName("StudyHall");
        assertEquals("StudyHall", ar1.getName());
    }

    @Test
    void getRoomID() {
        assertEquals(1, ar1.getRoomID());
    }

    @Test
    void setRoomID() {
        ar1.setRoomID(2);
        assertEquals(2, ar1.getRoomID());
    }

    @Test
    void getCapacity() {
        assertEquals(150, ar1.getCapacity());
    }

    @Test
    void setCapacity() {
        ar1.setCapacity(200);
        assertEquals(200, ar1.getCapacity());
    }

    @Test
    void isClicker() {
        assertTrue(ar1.isClicker());
    }

    @Test
    void setClicker() {
        ar1.setClicker(false);
        assertFalse(ar1.isClicker());
    }

    @Test
    void isPowerOutlets() {
        assertTrue(ar1.isPowerOutlets());
    }

    @Test
    void setPowerOutlets() {
        ar1.setPowerOutlets(false);
        assertFalse(ar1.isPowerOutlets());
    }

    @Test
    void isTv() {
        assertTrue(ar1.isTv());
    }

    @Test
    void setTv() {
        ar1.setTv(false);
        assertFalse(ar1.isTv());
    }

    @Test
    void isWhiteboard() {
        assertTrue(ar1.isWhiteboard());
    }

    @Test
    void setWhiteboard() {
        ar1.setWhiteboard(false);
        assertFalse(ar1.isWhiteboard());
    }
}