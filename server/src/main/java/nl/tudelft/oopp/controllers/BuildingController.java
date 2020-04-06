package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.Bike;
import nl.tudelft.oopp.entities.BikeReservation;
import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.Restaurant;
import nl.tudelft.oopp.entities.Room;
import nl.tudelft.oopp.entities.RoomReservation;
import nl.tudelft.oopp.entities.TimeSlot;
import nl.tudelft.oopp.repositories.BikeRepository;
import nl.tudelft.oopp.repositories.BikeReservationRepository;
import nl.tudelft.oopp.repositories.BuildingRepository;
import nl.tudelft.oopp.repositories.RestaurantRepository;
import nl.tudelft.oopp.repositories.RoomRepository;
import nl.tudelft.oopp.repositories.RoomReservationRepository;
import nl.tudelft.oopp.repositories.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BikeReservationRepository bikeReservationRepository;

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

    /**
     * adds a new building to the database only an admin can do this.
     * @param buildingName the name of the building.
     * @param nonReservableSpace a boolean saying if the the building has non reservable space.
     * @param carParkingSpaces the amount of car parking spaces
     * @param description a String description of the building
     * @param openingTime the time the building opens
     * @param closingTime the time the building closes
     * @author Kendra/Scott.
     */
    @PostMapping("addNewBuilding/{buildingName}/{nonReservableSpace}/{carParkingSpaces}/{description}/{openingTime}/{closingTime}")
    @ResponseBody
    public void addNewBuilding(@PathVariable (value = "buildingName") String buildingName,
                               @PathVariable (value = "nonReservableSpace") boolean nonReservableSpace,
                               @PathVariable (value = "carParkingSpaces") int carParkingSpaces,
                               @PathVariable (value = "description") String description,
                               @PathVariable (value = "openingTime") Time openingTime,
                                @PathVariable (value = "closingTime") Time closingTime) {

        String[] nameArray = buildingName.split("_");
        String name = nameArray[0];
        for (int i = 1; i < nameArray.length; i++) {
            name = name + " " + nameArray[i];
        }

        //adding spaces back that were removed during the building of the URL.
        String[] descriptionArray = description.split("_");
        String buildingDescription = descriptionArray[0];
        for (int i = 1; i < descriptionArray.length; i++) {
            buildingDescription = buildingDescription + " " + descriptionArray[i];
        }

        Building newBuilding = new Building(name, nonReservableSpace, carParkingSpaces, buildingDescription, openingTime, closingTime);

        System.out.println("Added a new building to the database");
        buildingRepository.save(newBuilding);
    }

    /**
     * deletes building from the database.
     * @param buildingName the building to be deleted
     */
    @DeleteMapping("deleteBuilding/{buildingName}")
    @ResponseBody
    public void deleteBuilding(@PathVariable (value = "buildingName") String buildingName) {
        try {
            String[] nameArray = buildingName.split("_");
            String name = nameArray[0];
            for (int i = 1; i < nameArray.length; i++) {
                name = name + " " + nameArray[i];
            }

            Optional<Building> b = buildingRepository.findById(name);
            Building building = b.get();
            buildingRepository.delete(building);
            System.out.println("building deleted successfully");

        } catch (Exception e) {
            throw new IllegalArgumentException("this input does not exist");
        }
    }
}

