package nl.tudelft.oopp.repositories;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import nl.tudelft.oopp.entities.RoomReservation;
import nl.tudelft.oopp.projections.StudentReservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer> {

    @Query(value = "SELECT DISTINCT room_id FROM room WHERE room_id NOT IN "
            + "(SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room"
            + " WHERE building_name = ?1 AND day = ?2 AND (start_time = ?3 AND end_time = ?4)) AND building_name = ?1", nativeQuery = true)
    List<Integer> findAllAvailableRooms(String buildingName, Date day, Time startTime, Time endTime);

    /**
     * A query which queries the DB for all available rooms in a specific building at a given timeslot
     * and also returns the rooms which can be overwritten by a user with a role that is higher
     * than the current reservation for a given timeslot.
     *
     * @author Niels Tomassen
     * @param buildingName the name of the building in which we are checking for avaialble rooms
     * @param day the day for which we are checking for available rooms is of type sql.Date
     * @param startTime the start time of a certain timeslot we are looking for rooms to reserve
     *                   startTime is of type sql.Time
     * @param endTime the end time of a certain timeslot we are looking for rooms to reserve
     *                endTime is of type sql.Time
     * @param roleFk the role of the user who wants to get a list of all availble rooms, used to check
     *               whether this user can override existing roomreservations
     * @return List of Integers containing the primary keys of all rooms that are available in
     *              a certain building for a specific timeslot at a specific date
     */
    @Query(value = "SELECT room_id FROM room WHERE room.building_name = ?1 AND (room_id NOT IN "
            + "(SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room"
            + " WHERE room.building_name = ?1 AND roomreservation.day = ?2 AND (timeslot.start_time = ?3 AND timeslot.end_time = ?4))) OR"
            + " (room_id IN (SELECT room_id FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room NATURAL JOIN user WHERE"
            + " room.building_name = ?1 AND roomreservation.day = ?2 AND (timeslot.start_time = ?3 AND timeslot.end_time = ?4)"
            + " AND user.role_fk < ?5))", nativeQuery = true)
    List<Integer> findAllAvailableRoomsWithOverriding(String buildingName, Date day, Time startTime, Time endTime, int roleFk);

    /**
     * A query which queries the DB for all room reservations which can be overridden in a specific building at a given timeslot
     * by a user with a role that is higher than the current reservation for a given timeslot.
     *
     * @author Niels Tomassen
     * @param buildingName the name of the building in which we are checking for avaialble rooms
     * @param day the day for which we are checking for available rooms is of type sql.Date
     * @param startTime the start time of a certain timeslot we are looking for rooms to reserve
     *                   startTime is of type sql.Time
     * @param endTime the end time of a certain timeslot we are looking for rooms to reserve
     *                endTime is of type sql.Time
     * @param roleFk the role of the user who wants to get a list of all availble rooms, used to check
     *               whether this user can override existing roomreservations
     * @return List of Integers containing the primary keys of all roomreservations that are available to
     *              override for a specific user
     */
    @Query(value = "SELECT DISTINCT reservation_id FROM roomreservation WHERE"
            + " reservation_id IN (SELECT DISTINCT reservation_id FROM room NATURAL JOIN timeslot NATURAL JOIN roomreservation NATURAL JOIN user WHERE"
            + " room.building_name = ?1 AND roomreservation.day = ?2 AND (timeslot.start_time = ?3 AND timeslot.end_time = ?4)"
            + " AND user.role_fk < ?5)", nativeQuery = true)
    List<Integer> findAllOverridableRoomReservations(String buildingName, Date day, Time startTime, Time endTime, int roleFk);


    @Query(value = "SELECT role_id, role_name, user_name, building_name, room_name, name, room_id, capacity, clicker, power_outlets, tv, whiteboard, reservation_id, timeslot_id "
            + "FROM roomreservation NATURAL JOIN timeslot NATURAL JOIN room NATURAL JOIN type NATURAL JOIN user JOIN role on (role_fk = role.role_id)"
            + " WHERE building_name = ?1 AND day = ?2 AND (start_time = ?3 AND end_time = ?4) AND role_id = ?5", nativeQuery = true)
    List<StudentReservations> findAllStudentReservations(String buildingName, Date day, Time startTime, Time endTime, Integer roleId);

    //role_id, role_name, user_name, building_name, room_name, name, room_id, capacity, clicker, power_outlets, tv, whiteboard, reservation_id, timeslot_id
}
