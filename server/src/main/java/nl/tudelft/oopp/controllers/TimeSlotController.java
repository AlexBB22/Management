package nl.tudelft.oopp.controllers;

import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.entities.TimeSlot;
import nl.tudelft.oopp.repositories.BuildingRepository;
import nl.tudelft.oopp.repositories.RoomRepository;
import nl.tudelft.oopp.repositories.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")
@Controller
public class TimeSlotController {
    /**
     * timeslot_id
     * room_fk
     * start_time
     * end_time
     */
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @PostMapping("/reserveTimeSlot/{room_id}/{building_id}")
    @ResponseBody
    public void reserveTimeSlot(@PathVariable (value= "room_id") Integer room_id,
                                @PathVariable (value= "building_id") String building_id,
                                @RequestBody TimeSlot timeslot) {
        TimeSlot newtimeslot=timeslot;
        Optional<Room> r=roomRepository.findById(room_id);
        Room room=r.get();


        Optional<Building> b=buildingRepository.findById(building_id);
        Building building=b.get();

        newtimeslot.setBuilding(building);
        newtimeslot.setRoom(room);

        room.addTimeslots(newtimeslot);
        System.out.println(newtimeslot.toString());
        timeSlotRepository.save(newtimeslot);
    }

}
