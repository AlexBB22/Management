package nl.tudelft.oopp.controllers;

import nl.tudelft.oopp.entities.*;
import nl.tudelft.oopp.repositories.*;
import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.repositories.BuildingRepository;
import nl.tudelft.oopp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")


@Controller
public class RoomReservationController {
    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private BuildingRepository buildingRepository;


    /**
     * Adds a RoomReservation to the DB for a specific user at a specific timeslot.
     * A new timeslot is initialized and is also saved in the DB
     *
     * @author Niels Tomassen
     * @param roomId the primary key of the room we want to reserve a room for
     * @param buildingName the primary key of the building we want to reserve a room in
     * @param day the day for which we want to reserve a room is of type sql.Date
     * @param startTime the start time of the timeslot for which we want to reserve a room
     *                  startTime is of type sql.Time
     * @param endTime the end time of the timeslot for which we want to reserve a room
     *                endTime is of type sql.Time
     * @param userId the primary key referring to the user who wants to add a RoomReservation
     * @return RoomReservation the RoomReservation which we are adding to the DB
     */
    @PostMapping(value = "/createNewReservation/{roomId}/{buildingName}/{Day}/{start_time}/{end_time}/{userId}")
    @ResponseBody
    public RoomReservation addRoomReservation(@PathVariable (value = "roomId") int roomId, @PathVariable (value = "buildingName") String buildingName,
                                              @PathVariable (value = "Day") Date day, @PathVariable (value = "start_time") Time startTime,
                                              @PathVariable (value = "end_time") Time endTime, @PathVariable (value = "userId") int userId) {

        //get the Building of a given name
        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        //get the room of a given id
        Optional<Room> r = roomRepository.findById(roomId);
        Room room = r.get();

        //get the user of a given id
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        //Initialize a timeslot and set up FK entity relationships with Room and Building
        TimeSlot timeslot = new TimeSlot(startTime, endTime);
        room.addTimeslots(timeslot);
        timeslot.setRoom(room);
        timeslot.setBuilding(building);

        TimeSlot DBtimeslot = timeSlotRepository.save(timeslot);

        //Making a new RoomReservation entity and setting up its FK entity relationships with TimeSlot and User
        RoomReservation roomReservation = new RoomReservation(day);
        roomReservation.setUser_fk(user);
        roomReservation.setTimeslot_fk(DBtimeslot);
        user.addRoomReservation(roomReservation);
        DBtimeslot.addRoomReservation(roomReservation);


        System.out.println("Added a new room reservation to DB: " + roomReservation.toString());
        return roomReservationRepository.save(roomReservation);
    }


    /**
     * A method which queries the database to find out which available rooms there are for a certain building at a certain
     * timeslot and date and also checks whether a user can override an existing reservation using the
     * findAllAvailableRoomsWithOverriding query.
     *
     * @param buildingName the name of the building in which we want to find all available rooms
     * @param day the day for which we want to find available rooms day is of type sql.Date
     * @param startTime the start_time of the timeslot for which we want to find all available rooms
     *                  startTime is of type sql.Time
     * @param endTime the end time of the timeslot for which we want to find all available rooms
     *                 endTime is of type sql.Time

     * @param userId the primary key of the user who is trying to find all available rooms, this primary key will be used
     *               to determine the role of the user and thus find out if they can override some existing room reservations
     * @return List of rooms the list of rooms which are available for reserving in a specific building at a specific date and timeslot
     *                  for a certain user
     */

    @GetMapping("getAvailableRooms/{buildingName}/{Day}/{start_time}/{end_time}/{user_id}")
    @ResponseBody
    public List<Room> getAvailableRooms(@PathVariable(value = "buildingName") String buildingName,
                                           @PathVariable (value = "Day") Date day,
                                           @PathVariable (value = "start_time") Time startTime,
                                           @PathVariable (value = "end_time") Time endTime,
                                           @PathVariable (value = "user_id") int userId) {
        //Initializes a user from the given user primary key
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        //Queries the database using the role of the just initialized user
        List<Integer> objects = roomReservationRepository.findAllAvailableRoomsWithOverriding(buildingName, day, startTime,
                endTime, user.getRole().getRole_id());

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Optional<Room> r = roomRepository.findById(objects.get(i));
            Room room = r.get();
            rooms.add(room);
        }
        return rooms;
        //return objects;
    }

}
