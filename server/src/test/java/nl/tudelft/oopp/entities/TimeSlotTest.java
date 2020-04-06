package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TimeSlotTest {

    @Test
    public void timeSlotConstructor() {
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        TimeSlot ts = new TimeSlot(time, time2);
        assertNotNull(ts);
    }

    @Test
    public void getTimeSlotId() {
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        TimeSlot ts = new TimeSlot(time, time2);
        assertEquals(0, ts.getTimeslot_id());
    }

    @Test
    public void setTimeSlotId() {
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        TimeSlot ts = new TimeSlot(time, time2);
        ts.setTimeslot_id(10);
        assertEquals(10, ts.getTimeslot_id());
    }

    @Test
    public void getSetBuilding() {
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        Building building = new Building("EWI", true, 2, "creepy people with glasses walking around", time, time2);
        TimeSlot ts = new TimeSlot(time, time2);
        ts.setBuilding(building);
        assertEquals(building, ts.getBuilding());
    }

    @Test
    public void getSetRoom() {
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        TimeSlot ts = new TimeSlot(time, time2);
        Room room = new Room(10, "smexy");
        ts.setRoom(room);
        assertEquals(room, ts.getRoom());
    }

    @Test
    public void toStringTest() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        Room room = new Room(10, "smexy");
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        TimeSlot ts = new TimeSlot(time, time2);
        ts.setBuilding(building);
        ts.setRoom(room);
        String toString = "TimeSlot{"
                + "timeslot_id=" + ts.getTimeslot_id()
                + ", building_id=" + "EWI"
                + ", room=" + "smexy"
                + ", start_time=" + time
                + ", end_time=" + time2
                + '}';
        assertEquals(toString, ts.toString());
    }

    @Test
    public void getSetRoomReservation() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        Room room = new Room(10, "smexy");
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        Building building = new Building("EWI", true, 300, "Tallest building on campus, has an elevator", time, time2);
        TimeSlot ts = new TimeSlot(time, time2);
        ts.setBuilding(building);
        ts.setRoom(room);
        List<RoomReservation> roomlist = new ArrayList<RoomReservation>();
        ts.setRoomReservations(roomlist);
        ts.addRoomReservation(rs);
        assertEquals(ts.getRoomReservations(), roomlist);
    }

    @Test
    public void equalsMethod() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        TimeSlot ts = new TimeSlot(time, time2);
        TimeSlot ts1 = new TimeSlot(time, time2);
        assertEquals(ts1, ts);
    }

    @Test
    public void getSetStartTimeEndTime() {
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        Room room = new Room(10, "smexy");
        Time time = new Time(10, 20, 30);
        Time time2 = new Time(20, 30, 40);
        TimeSlot ts = new TimeSlot(time, time2);
        ts.setStart_time(time2);
        ts.setEnd_time(time);
        assertEquals(ts.getStart_time(), time2);
        assertEquals(ts.getEnd_time(), time);
    }

    @Test
    void removeRoomReservationTest() {
        TimeSlot ts = new TimeSlot(new Time(12345), new Time(6789));
        RoomReservation r1 = new RoomReservation(new Date(20200402));
        RoomReservation r2 = new RoomReservation(new Date(20200403));
        List<RoomReservation> roomReservations = new ArrayList<>();
        roomReservations.add(r1);
        roomReservations.add(r2);
        ts.setRoomReservations(roomReservations);
        ts.removeRoomReservation(r2);

        List<RoomReservation> actual = new ArrayList<>();
        actual.add(r1);

        assertEquals(actual, ts.getRoomReservations());

    }

    @Test
    void equalsTest2() {
        TimeSlot ts = new TimeSlot(new Time(12345), new Time(6789));
        TimeSlot ts2 = new TimeSlot(new Time(12345), new Time(9874));
        assertNotEquals(ts, ts2);
    }

    @Test
    void hashCodeTest() {
        TimeSlot ts = new TimeSlot(new Time(12345), new Time(6789));
        assertEquals(12345, ts.hashCode());
    }

}
