package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.Type;
import nl.tudelft.oopp.demo.repositories.BuildingRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")


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
    Method adds a new building to the table. It takes in the name of the building and the type id (to setup FK)
     */
    @PostMapping("/addRoomToDB/{buildingName}/{typeId}")
    @ResponseBody
    public void addRoomToDB(@PathVariable (value = "buildingName") String buildingName,
                            @PathVariable (value = "typeId") int typeId,
                            @RequestBody Room room) {
        Room newroom = room;
        //get the Building of given name
        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        //get the Type of given id
        Optional<Type> t = typeRepository.findById(typeId);
        Type type = t.get();

        //use helper methods to make sure both sides of relationship have updated their attributes
        building.addRoom(newroom);
        type.addRoom(newroom);

        System.out.println("Added a new room to DB: " + newroom.toString());
        roomRepository.save(newroom);
    }

    @GetMapping("test2")
    @ResponseBody
    public int test2() {
        List<Room> rooms = roomRepository.testing("DW");
        return rooms.size();
    }

//    @GetMapping("test1")
//    public void test() {
//        Optional<Building> b = buildingRepository.findById("DW");
//        Optional<Type> t = typeRepository.findById(3);
//        Building building = b.get();
//        Type type = t.get();
//        List<Room> bRooms = building.getRooms();
//        List<Room> tRooms = type.getListOfRooms();
//        System.out.println(bRooms.size());
//        System.out.println(tRooms.size());
//    }
}
