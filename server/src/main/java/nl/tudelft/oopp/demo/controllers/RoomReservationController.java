package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("getAvailableRooms/{buildingName}/{Day}/{start_time}/{end_time}")
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
