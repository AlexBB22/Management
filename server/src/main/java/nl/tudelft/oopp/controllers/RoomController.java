package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

@RestController
public class RoomController {
    @Autowired
    private RoomRepository roomrep;

    @GetMapping("/getrooms")
    @ResponseBody
    public List<Room> getAllRooms() {
        return roomrep.findAll();
    }

}
