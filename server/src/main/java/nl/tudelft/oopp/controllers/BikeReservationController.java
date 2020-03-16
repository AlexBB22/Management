package nl.tudelft.oopp.controllers;

import java.sql.Date;
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
     * @param bikeId - the bike that is being reserved
     * @param buildingName - the name of the building where the bike is located
     * @param day - time when the bike will be reserved
     * @param userId - information about the person who is doing the reservation
     * @return a new BikeReservation
     *
     * @author Sartori Kendra
     */
    @PostMapping("/addBikeReservation/{bikeId}/{buildingName}/{day}/{userId}")
    @ResponseBody
    public BikeReservation addBikeReservation(@PathVariable (value = "bikeId") int bikeId,
                                              @PathVariable (value = "buildingName") String buildingName,
                                              @PathVariable (value = "day") Date day,
                                              @PathVariable (value = "userId") int userId) {

        Optional<Building> b = buildingRepository.findById(buildingName);
        Building building = b.get();

        Optional<Bike> b1 = bikeRepository.findById(bikeId);
        Bike bike = b1.get();

        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        BikeReservation bikeReservation = new BikeReservation(day);
        bikeReservation.setBike_user_fk(user);
        bikeReservation.setBike_fk(bike);
        user.addBikeReservation(bikeReservation);

        System.out.println("Added a new bike reservation");
        return bikeReservationRepository.save(bikeReservation);
    }
}
