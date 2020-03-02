package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.*;
import nl.tudelft.oopp.demo.repositories.*;
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

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")


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

    @PostMapping(value = "/createNewReservation/{roomId}/{buildingName}/{Day}/{start_time}/{end_time}/{userId}")
    @ResponseBody
    public RoomReservation addRoomReservation(@PathVariable (value = "roomId") int roomId, @PathVariable String buildingName, @PathVariable Date Day,
                                              @PathVariable Time start_time, @PathVariable Time end_time, @PathVariable int userId) {

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
        TimeSlot timeslot = new TimeSlot(start_time, end_time);
        room.addTimeslots(timeslot);
        timeslot.setRoom(room);
        timeslot.setBuilding(building);

        TimeSlot DBtimeslot = timeSlotRepository.save(timeslot);

        //Making a new RoomReservation entity and setting up its FK entity relationships with TimeSlot and User
        RoomReservation roomReservation = new RoomReservation(Day);
        roomReservation.setUser_fk(user);
        roomReservation.setTimeslot_fk(DBtimeslot);
        user.addRoomReservation(roomReservation);
        DBtimeslot.addRoomReservation(roomReservation);


        System.out.println("Added a new room reservation to DB: " + roomReservation.toString());
        return roomReservationRepository.save(roomReservation);
    }




    @GetMapping("getAvailableRooms/{buildingName}/{`Day`}/{start_time}/{end_time}")
    @ResponseBody
    public List<Room> getAvailableRooms(@PathVariable(value = "buildingName") String building_name,
                                           @PathVariable (value = "Day") Date day,
                                           @PathVariable (value = "start_time") Time start_time,
                                           @PathVariable (value = "end_time") Time end_time)
    {
        List<Integer> objects = roomReservationRepository.findAllAvailableRooms(building_name, day, start_time, end_time);
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
