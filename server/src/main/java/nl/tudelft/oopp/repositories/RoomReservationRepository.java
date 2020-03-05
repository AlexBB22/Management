package nl.tudelft.oopp.repositories;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import nl.tudelft.oopp.entities.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {
    /*
    SELECT room_id FROM room
    WHERE room_id NOT IN (SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room
      WHERE building_name = "DW" AND day = "2020-03-01" AND (start_time = '08:45:00' AND end_time = '10:45:00'))
   */
    @Query(value = "SELECT room_id FROM room WHERE room_id NOT IN "
            + "(SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room"
            + " WHERE building_name = ?1 AND day = ?2 AND (start_time = ?3 AND end_time = ?4)) AND building_name = ?1", nativeQuery = true)
    List<Integer> findAllAvailableRooms(String buildingName, Date day, Time startTime, Time endTime);
    /*
    I needed to get a list of the room_ids and not the room itself as nativeQuery returns a list of Objects.
    Thus, we need to figure out a way to convert that Objects into Rooms. So instead, I got it to return a list of integers
    that represents room_ids of the Rooms that are available. Then in the controller, I query the DB and get each coressponding
    room for the list of ids. And return that list to the user (see RoomReservationController). s
     */

}
//write pre-defined methods for SELECTS using distinct
