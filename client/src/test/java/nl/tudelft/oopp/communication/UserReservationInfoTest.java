package nl.tudelft.oopp.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class UserReservationInfoTest {

    private UserReservationInfo uri1;

    @BeforeEach
    void setUp() {
        uri1 = new UserReservationInfo(1, "2020-04-01", "08:45:00", "10:45:00", "DW", "DW-IZ4", "StudyHall");
    }

    @Test
    void emptyConstructorTest() {
        UserReservationInfo uri2 = new UserReservationInfo();
        assertNotNull(uri2);
    }

    @Test
    void constructorTest() {
        assertNotNull(uri1);
    }


    @Test
    void getReservationID() {
        assertEquals(1, uri1.getReservationID());
    }

    @Test
    void setReservationID() {
        uri1.setReservationID(2);
        assertEquals(2, uri1.getReservationID());
    }

    @Test
    void getDay() {
        assertEquals("2020-04-01", uri1.getDay());
    }

    @Test
    void setDay() {
        uri1.setDay("2020-04-02");
        assertEquals("2020-04-02", uri1.getDay());
    }

    @Test
    void getStartTime() {
        assertEquals("08:45:00", uri1.getStartTime());
    }

    @Test
    void setStartTime() {
        uri1.setStartTime("10:45:00");
        assertEquals("10:45:00", uri1.getStartTime());

    }

    @Test
    void getEndTime() {
        assertEquals("10:45:00", uri1.getEndTime());
    }

    @Test
    void setEndTime() {
        uri1.setEndTime("12:45:00");
        assertEquals("12:45:00", uri1.getEndTime());
    }

    @Test
    void getBuildingName() {
        assertEquals("DW", uri1.getBuildingName());
    }

    @Test
    void setBuildingName() {
        uri1.setBuildingName("CSE");
        assertEquals("CSE", uri1.getBuildingName());
    }

    @Test
    void getRoomName() {
        assertEquals("DW-IZ4", uri1.getRoomName());
    }

    @Test
    void setRoomName() {
        uri1.setRoomName("DW-IZ1");
        assertEquals("DW-IZ1", uri1.getRoomName());
    }

    @Test
    void getName() {
        assertEquals("StudyHall", uri1.getName());
    }

    @Test
    void setName() {
        uri1.setName("LectureHall");
        assertEquals("LectureHall", uri1.getName());

    }

    @Test
    void testToString() {
        String res = "UserReservationInfo{reservationID=1, day='2020-04-01', startTime='08:45:00', endTime='10:45:00', buildingName='DW', roomName='DW-IZ4', name='StudyHall'}";
        assertEquals(res, uri1.toString());
    }
}