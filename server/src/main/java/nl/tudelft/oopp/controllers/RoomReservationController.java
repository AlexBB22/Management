package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.entities.RoomReservation;
import nl.tudelft.oopp.entities.TimeSlot;
import nl.tudelft.oopp.entities.User;

import nl.tudelft.oopp.repositories.BuildingRepository;
import nl.tudelft.oopp.repositories.RoomRepository;
import nl.tudelft.oopp.repositories.RoomReservationRepository;
import nl.tudelft.oopp.repositories.TimeSlotRepository;
import nl.tudelft.oopp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;



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
     * Method to add a new room reservation into the database.
     * @param roomId - the room which is to be reserved.
     * @param buildingName - the building in which the room is in.
     * @param day - the date of reservation.
     * @param startTime - the starttime
     * @param endTime - the endtime
     * @param userId - the user which has reserved the room
     * @return
     */
    @PostMapping(value = "createNewReservation/{roomId}/{buildingName}/{day}/{startTime}/{endTime}/{userId}")
    @ResponseBody
    public RoomReservation addRoomReservation(@PathVariable (value = "roomId") int roomId, @PathVariable String buildingName, @PathVariable Date day,
                                              @PathVariable Time startTime, @PathVariable Time endTime, @PathVariable int userId) {

        //get the Building of a given name
        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        //get the room of a given id
        Optional<Room> r = roomRepository.findById(roomId);
        Room room = r.get();

        //Initialize a timeslot and set up FK entity relationships with Room and Building
        TimeSlot timeslot = new TimeSlot(startTime, endTime);
        room.addTimeslots(timeslot);
        timeslot.setRoom(room);
        timeslot.setBuilding(building);

        TimeSlot dbTimeslot = timeSlotRepository.save(timeslot);

        //get the user of a given id
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        //Making a new RoomReservation entity and setting up its FK entity relationships with TimeSlot and User
        RoomReservation roomReservation = new RoomReservation(day);
        roomReservation.setUser_fk(user);
        roomReservation.setTimeslot_fk(dbTimeslot);
        user.addRoomReservation(roomReservation);
        dbTimeslot.addRoomReservation(roomReservation);


        System.out.println("Added a new room reservation to DB: " + roomReservation.toString());
        return roomReservationRepository.save(roomReservation);
    }


    /**
     * Method to get all available rooms.
     * @param buildingName - the building name where the room is
     * @param day - the date
     * @param startTime - the starttime
     * @param endTime - the endtime
     * @return
     */
    @GetMapping("getAvailableRooms/{buildingName}/{Day}/{start_time}/{end_time}")
    @ResponseBody
    public List<Room> getAvailableRooms(@PathVariable(value = "buildingName") String buildingName,
                                           @PathVariable (value = "Day") Date day,
                                           @PathVariable (value = "start_time") Time startTime,
                                           @PathVariable (value = "end_time") Time endTime) {
        List<Integer> objects = roomReservationRepository.findAllAvailableRooms(buildingName, day, startTime, endTime);
        List<Room> rooms = new ArrayList<>();
        for (int i: objects) {
            Optional<Room> r = roomRepository.findById(i);
            Room room = r.get();
            rooms.add(room);
        }
        return rooms;
        //return objects;
    }

}
