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

public class RoomReservationTest {

    @Test
    public void roomReservationConstructor() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        assertNotNull(rs);
    }

    @Test
    void emptyConstructorTest() {
        RoomReservation r1 = new RoomReservation();
        assertNotNull(r1);
    }

    @Test
    public void getRoomReservationId() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        assertEquals(0, rs.getReservation_id());
    }

    @Test
    public void setRoomReservationId() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        rs.setReservation_id(10);
        assertEquals(10, rs.getReservation_id());
    }

    @Test
    public void getSetUserId() {
        User user = new User(2, "email", "username", "password");
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        rs.setUser_fk(user);
        assertEquals(user, rs.getUser_fk());
    }

    @Test
    public void getSetTimeSlot() {
        Date date = new Date(2020, 3, 10);
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(10, 40, 0);
        TimeSlot ts = new TimeSlot(time, time2);
        RoomReservation rs = new RoomReservation(date);
        rs.setTimeslot_fk(ts);
        assertEquals(ts, rs.getTimeslot_fk());
    }

    @Test
    public void getDate() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        assertEquals(date, rs.getDay());
    }

    @Test
    public void setDate() {
        Date date = new Date(2020, 3, 10);
        Date date2 = new Date(2021, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        rs.setDay(date2);
        assertEquals(date2, rs.getDay());
    }

}
