package nl.tudelft.oopp.controllers;

import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.entities.Type;
import nl.tudelft.oopp.repositories.BuildingRepository;
import nl.tudelft.oopp.repositories.RoomRepository;
import nl.tudelft.oopp.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")


@Controller
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private TypeRepository typeRepository;



    @GetMapping("/getListOfRooms")
    @ResponseBody
    public List<Room> getListOfRooms() {
        return roomRepository.findAll();
    }

    /*
    Method adds a new room to the table. It takes in the name of the building and the type id (to setup FK)
     */

    /**
     * End point to add a new room to the database.
     * @param buildingName - the building in which the room is in
     * @param typeId - the type of the room
     * @param room - the room name
     */
    @PostMapping("/addRoomToDB/{buildingName}/{typeId}")
    @ResponseBody
    public void addRoomToDB(@PathVariable(value = "buildingName") String buildingName,
                            @PathVariable (value = "typeId") int typeId,
                            @RequestBody Room room) {
        //Room newroom = room;
        //get the Building of given name
        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        //get the Type of given id
        Optional<Type> t = typeRepository.findById(typeId);
        Type type = t.get();

        //use helper methods to make sure both sides of relationship have updated their attributes
        building.addRoom(room);
        type.addRoom(room);

        System.out.println("Added a new room to DB: " + room.toString());
        roomRepository.save(room);
    }

    @GetMapping("test2")
    @ResponseBody
    public int test2() {
        List<Room> rooms = roomRepository.testing("DW");
        return rooms.size();
    }
}
