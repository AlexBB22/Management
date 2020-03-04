package nl.tudelft.oopp.controllers;

import java.util.Optional;
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

@EnableJpaRepositories("nl.tudelft.oopp.repositories")
@Controller
public class TimeSlotController {
    /**.
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
    public void reserveTimeSlot(@PathVariable (value = "room_id") Integer roomId,
                                @PathVariable (value = "building_id") String buildingId,
                                @RequestBody TimeSlot timeslot) {

        Optional<Room> r = roomRepository.findById(roomId);
        Room room = r.get();


        Optional<Building> b = buildingRepository.findById(buildingId);
        Building building = b.get();

        timeslot.setBuilding(building);
        timeslot.setRoom(room);

        room.addTimeslots(timeslot);
        System.out.println(timeslot.toString());
        timeSlotRepository.save(timeslot);
    }

}
