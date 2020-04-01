package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OverridableRoomTest {

    private OverridableRoom or1;
    private OverridableRoom or2;

    @BeforeEach
    void setUp() {
        or1 = new OverridableRoom(1, "Student", "Kanish", "DW", "DW-IZ4", "StudyHall", 4, 50,
                false, true, false, true, 6, 9);
        or2 = new OverridableRoom();
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(or2);
    }

    @Test
    void constructorTest() {
        assertNotNull(or1);
    }

    @Test
    void getRoleID() {
        assertEquals(1, or1.getRoleID());
    }

    @Test
    void setRoleID() {
        or1.setRoleID(10);
        assertEquals(10, or1.getRoleID());
    }

    @Test
    void getRoleName() {
        assertEquals("Student", or1.getRoleName());
    }

    @Test
    void setRoleName() {
        or1.setRoleName("Teacher");
        assertEquals("Teacher", or1.getRoleName());
    }

    @Test
    void getUserName() {
        assertEquals("Kanish", or1.getUserName());
    }

    @Test
    void setUserName() {
        or1.setUserName("Niels");
        assertEquals("Niels", or1.getUserName());
    }

    @Test
    void getBuildingName() {
        assertEquals("DW", or1.getBuildingName());
    }

    @Test
    void setBuildingName() {
        or1.setBuildingName("CSE");
        assertEquals("CSE", or1.getBuildingName());
    }

    @Test
    void getRoomName() {
        assertEquals("DW-IZ4", or1.getRoomName());
    }

    @Test
    void setRoomName() {
        or1.setRoomName("DW-IZ1");
        assertEquals("DW-IZ1", or1.getRoomName());
    }

    @Test
    void getName() {
        assertEquals("StudyHall", or1.getName());
    }

    @Test
    void setName() {
        or1.setName("LectureHall");
        assertEquals("LectureHall", or1.getName());
    }

    @Test
    void getRoomID() {
        assertEquals(4, or1.getRoomID());
    }

    @Test
    void setRoomID() {
        or1.setRoomID(15);
        assertEquals(15, or1.getRoomID());
    }

    @Test
    void getCapacity() {
        assertEquals(50, or1.getCapacity());
    }

    @Test
    void setCapacity() {
        or1.setCapacity(100);
        assertEquals(100, or1.getCapacity());
    }

    @Test
    void isClicker() {
        assertFalse(or1.isClicker());
    }

    @Test
    void setClicker() {
        or1.setClicker(true);
        assertTrue(or1.isClicker());
    }

    @Test
    void isPowerOutlets() {
        assertTrue(or1.isPowerOutlets());
    }

    @Test
    void setPowerOutlets() {
        or1.setPowerOutlets(false);
        assertFalse(or1.isPowerOutlets());
    }

    @Test
    void isTv() {
        assertFalse(or1.isTv());
    }

    @Test
    void setTv() {
        or1.setTv(true);
        assertTrue(or1.isTv());
    }

    @Test
    void isWhiteboard() {
        assertTrue(or1.isWhiteboard());
    }

    @Test
    void setWhiteboard() {
        or1.setWhiteboard(false);
        assertFalse(or1.isWhiteboard());
    }

    @Test
    void getReservationID() {
        assertEquals(6, or1.getReservationID());
    }

    @Test
    void setReservationID() {
        or1.setReservationID(46);
        assertEquals(46, or1.getReservationID());
    }

    @Test
    void getTimeslotID() {
        assertEquals(9, or1.getTimeslotID());
    }

    @Test
    void setTimeslotID() {
        or1.setTimeslotID(75);
        assertEquals(75, or1.getTimeslotID());
    }

    @Test
    void testToString() {
        String res = "OverridableRoom{roleID=1, roleName='Student', userName='Kanish', buildingName='DW', "
                + "roomName='DW-IZ4', name='StudyHall', roomID=4, capacity=50, clicker=false, powerOutlets=true, "
                + "tv=false, whiteboard=true, reservationID=6, timeslotID=9}";
        assertEquals(res, or1.toString());
    }
}