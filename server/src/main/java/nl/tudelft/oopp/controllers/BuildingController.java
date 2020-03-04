package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("buildings/")
    @ResponseBody
    public String emptyBuilding() {
        return "Please put in a building";
    }

    @GetMapping("buildings/All")
    @ResponseBody
    public List<Building>  getAllBuildings() {
        return buildingRepository.findAll();
    }

    /**.
     * find a building by name
     * @param buildingName name of building to search for
     * @return building matching buildingName
     * @Author Scott Jochems
     */
    @GetMapping("buildings/{buildingName}")
    @ResponseBody
    public Building findByBuildingName(@PathVariable String buildingName) {
        try {
            List<Building> allBuildings = buildingRepository.findAll();
            for (int i = 0; i < allBuildings.size(); i++) {
                if (allBuildings.get(i).getBuilding_Name().equals(buildingName)) {
                    return allBuildings.get(i);
                }
            }
            throw new IllegalArgumentException("this building does not exist");
        } catch (Exception e) {
            throw new IllegalArgumentException("this input does not exist");
        }
    }

    /**
     * End point method that gets all building.
     * @param buildingName - name of the building to find
     * @param date - the date at which we want to get buildings
     * @return A list of rooms that are available for a certain building at a date
     */
    @GetMapping("buildings/{buildingName}/{date}")
    @ResponseBody
    public List<Room> findRoomsInBuilding(@PathVariable String buildingName, Date date) {
        List<Room> roomsInBuilding = new ArrayList<Room>();
        try {
            List<Building> allBuildings = buildingRepository.findAll();
            for (int i = 0; i < allBuildings.size(); i++) {
                if (allBuildings.get(i).getBuilding_Name().equals(buildingName)) {
                    roomsInBuilding = getAllBuildings().get(i).getRooms();
                }
            }
            return roomsInBuilding;
        } catch (Exception x) {
            throw new IllegalArgumentException("This building does not exist");
        }
    }
}

