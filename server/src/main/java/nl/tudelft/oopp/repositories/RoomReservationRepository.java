package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {


    /*
    SELECT room_id FROM room
    WHERE room_id NOT IN (SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room
      WHERE building_name = "DW" AND day = "2020-03-01" AND (start_time = '08:45:00' AND end_time = '10:45:00'))
   */
    @Query(value = "SELECT room_id FROM room WHERE room_id NOT IN " +
            "(SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room" +
            " WHERE building_name = ?1 AND day = ?2 AND (start_time = ?3 AND end_time = ?4))", nativeQuery = true)
    List<Integer> findAllAvailableRooms(String building_name, Date day, Time start_time, Time end_time);
    /*
    I needed to get a list of the room_ids and not the room itself as nativeQuery returns a list of Objects.
    Thus, we need to figure out a way to convert that Objects into Rooms. So instead, I got it to return a list of integers
    that represents room_ids of the Rooms that are available. Then in the controller, I query the DB and get each coressponding
    room for the list of ids. And return that list to the user (see RoomReservationController). s
     */



    /**
     * A query which queries the DB for all available rooms in a specific building at a given timeslot
     * and also returns the rooms which can be overwritten by a user with a role that is higher
     * than the current reservation for a given timeslot.
     *
     * @author Niels Tomassen
     * @param building_name the name of the building in which we are checking for avaialble rooms
     * @param day the day for which we are checking for available rooms is of type sql.Date
     * @param start_time the start time of a certain timeslot we are looking for rooms to reserve
     * start_time is of type sql.Time
     * @param end_time the end time of a certain timeslot we are looking for rooms to reserve
     * end_time is of type sql.Time
     * @param role_fk the role of the user who wants to get a list of all availble rooms, used to check
     * whether this user can override existing roomreservations
     * @return List<Integer> a list of Integers containing the primary keys of all rooms that are available in
     * a certain building for a specific timeslot at a specific date
     */
    @Query(value = "SELECT room_id FROM room WHERE room.building_name = ?1 AND (room_id NOT IN " +
            "(SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room" +
            " WHERE room.building_name = ?1 AND roomreservation.day = ?2 AND (timeslot.start_time = ?3 AND timeslot.end_time = ?4))) OR" +
            " (room_id IN (SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room NATURAL JOIN user WHERE" +
            " room.building_name = ?1 AND roomreservation.day = ?2 AND (timeslot.start_time = ?3 AND timeslot.end_time = ?4)" +
            " AND user.role_fk < ?5))", nativeQuery = true)
    List<Integer> findAllAvailableRoomsWithOverriding(String building_name, Date day, Time start_time, Time end_time, int role_fk);

}
//write pre-defined methods for SELECTS using distinct
