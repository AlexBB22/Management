package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.Bike;
import nl.tudelft.oopp.entities.BikeReservation;
import nl.tudelft.oopp.entities.Building;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.BikeRepository;
import nl.tudelft.oopp.repositories.BikeReservationRepository;
import nl.tudelft.oopp.repositories.BuildingRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BikeReservationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BikeReservationRepository bikeReservationRepository;

    /**
     * This method makes it possible to reserve a bike.
     *
     * @param buildingName - the name of the building where the bike is located
     * @param day          - time when the bike will be reserved
     * @param userId       - information about the person who is doing the reservation
     *
     * @author Sartori Kendra
     */
    @PostMapping("/addBikeReservation/{buildingName}/{day}/{userId}")
    @ResponseBody
    public void addBikeReservation(@PathVariable(value = "buildingName") String buildingName,
                                   @PathVariable(value = "day") Date day,
                                   @PathVariable(value = "userId") int userId) {

        int reservationsInBuilding = 0;

        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        List<Bike> listOfBikesNearBuilding = building.getBikes();
        int numberOfBikesNearBuilding = listOfBikesNearBuilding.size();

        List<BikeReservation> bikeReservationsAll = bikeReservationRepository.findAll();

        for (BikeReservation r : bikeReservationsAll) {
            Date date = r.getDay();
            Building b2 = r.getBuilding();
            if (day.compareTo(date) == 0 && b2.equals(building)) {
                reservationsInBuilding++;
            }
        }

        Optional<User> u = userRepository.findById(userId);
        User user = u.get();


        if (reservationsInBuilding < numberOfBikesNearBuilding) {
            BikeReservation bikeReservation = new BikeReservation(day);
            bikeReservation.setBike_user_fk(user);
            bikeReservation.setBuilding(building);
            user.addBikeReservation(bikeReservation);
            building.addBikeReservation(bikeReservation);

            System.out.println("Added a new bike reservation");
            bikeReservationRepository.save(bikeReservation);

        } else {
            System.out.println("There are no available bikes to reserve");
        }

    }

    /**
     * This is a get method which returns the available bikes near a building.
     * @param buildingName This is the building name.
     * @param day This is the day the user selects.
     * @return The method returns a integer, the number of available bikes.
     */
    @GetMapping("/availableBikesNumber/{buildingName}/{day}")
    @ResponseBody
    public int availableBikesNumber(@PathVariable(value = "buildingName") String buildingName,
                                     @PathVariable(value = "day") Date day) {
        int reservationsInBuilding = 0;

        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        List<Bike> listOfBikesNearBuilding = building.getBikes();
        int numberOfBikesNearBuilding = listOfBikesNearBuilding.size();

        List<BikeReservation> bikeReservationsAll = bikeReservationRepository.findAll();

        for (BikeReservation r : bikeReservationsAll) {
            Date date = r.getDay();
            Building b2 = r.getBuilding();
            if (day.compareTo(date) == 0 && b2.equals(building)) {
                reservationsInBuilding++;
            }
        }
        return numberOfBikesNearBuilding - reservationsInBuilding;
    }

    @GetMapping("/bikeReservationsForUser/{userID}")
    @ResponseBody
    public List<String> getBikeReservationsPerUser(@PathVariable (value = "userID") int userID) {

        List<BikeReservation> bikeReservationsAll = bikeReservationRepository.findAll();
        List<String> reservationsForUser = new ArrayList<>();

        for(BikeReservation r : bikeReservationsAll) {
            if(r.getBike_user_fk().getUser_id() == (userID)) {
                reservationsForUser.add(r.toString());
            }
        }
        return reservationsForUser;

    }

}
