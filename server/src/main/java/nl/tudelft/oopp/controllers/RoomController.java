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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/addRoomToDB/{buildingName}/{typeId}")
    @ResponseBody
    public void addRoomToDB(@PathVariable (value = "buildingName") String buildingName,
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
