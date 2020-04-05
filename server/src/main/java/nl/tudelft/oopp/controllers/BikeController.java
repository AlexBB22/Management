package nl.tudelft.oopp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.Bike;
import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.repositories.BikeRepository;
import nl.tudelft.oopp.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    /**
     * A method that allows adding a new bike to the database.
     * @param buildingName - the name of the location of the bike
     * @param bike - the new bike that needs to be added to the database
     *
     * @author Sartori Kendra
     */
    @PostMapping("/addBike/{building_name}")
    @ResponseBody
    public void addBike(@PathVariable(value = "building_name") String buildingName,
                        @RequestBody Bike bike) {
        Bike newBike = bike;
        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        building.addBike(newBike);

        System.out.println("A new bike has been added.");
        bikeRepository.save(newBike);
    }

    /**
     * This method allows the user to see a list of bikes located near a certain building.
     * @param buildingName - the location where the user searches for bikes
     * @return a list of bikes
     *
     * @author Sartori Kendra
     */
    @GetMapping("/getListOfBikes/{buildingName}")
    @ResponseBody
    public List<Bike> getBikeList(@PathVariable String buildingName) {
        List<Bike> bikeInBuilding = new ArrayList<>();
        List<Building> b = buildingRepository.findByBuildingName(buildingName);
        Building building = b.get(0);

        bikeInBuilding = bikeRepository.getBikesByBuildingName(building.getBuilding_Name());
        return bikeInBuilding;
    }

    /**
     * adds bikes to a building.
     * @param amount the amount of bikes to add
     * @param buildingName the name of the building it should be added to
     * @author Scott.
     */
    @PostMapping("/addBikes/{amount}/{buildingName}")
    @ResponseBody
    public void addBikes(@PathVariable (value = "amount") int amount, @PathVariable(value = "buildingName") String buildingName) {
        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();
        for (int i = 0; i < amount; i++) {
            Bike bike = new Bike();
            building.addBike(bike);
            bikeRepository.save(bike);
        }
    }
}