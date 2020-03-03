package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.repositories.BuildingRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("buildings/")
    @ResponseBody
    public String emptyBuilding(){ return "Please put in a building";}

    @GetMapping("buildings/All")
    @ResponseBody
    public List<Building>  getAllBuildings(){
        return buildingRepository.findAll();
    }

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
        }
        catch(Exception e){
            throw new IllegalArgumentException("this input does not exist");
        }
    }

    @GetMapping("buildings/{buildingName}/{date}")
    @ResponseBody
    public List<Room> findRoomsInBuilding(@PathVariable String buildingName, Date date){
        List<Room> roomsInBuilding= new ArrayList<Room>();
        try{
            List<Building> allBuildings = buildingRepository.findAll();
            for (int i = 0; i < allBuildings.size(); i++) {
                if (allBuildings.get(i).getBuilding_Name().equals(buildingName)) {
                    roomsInBuilding=getAllBuildings().get(i).getRooms();
                }
            }
            return roomsInBuilding;
        }
        catch(Exception x){
            throw new IllegalArgumentException("This building does not exist");
        }
    }

    /**
     * This method allows the admin to add a new building name.
     * @param buildingName - the name/identifier of the building that needs to be added
     * @param building - the building that needs to be added
     *
     * @author Sartori Kendra
     */
    @PostMapping("/addNewBuilding/{buildingName}")
    @ResponseBody
    public void addNewBuilding(@PathVariable (value = "buildingName") String buildingName,
                                @RequestBody Building building) {

        Building newBuilding = building;

        newBuilding.setBuilding_name(buildingName);

        System.out.println("Added a new building to the database");
        buildingRepository.save(newBuilding);
    }
}

