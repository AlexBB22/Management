package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.BikeReservation;
import nl.tudelft.oopp.entities.Food;
import nl.tudelft.oopp.entities.FoodReservation;
import nl.tudelft.oopp.entities.Restaurant;
import nl.tudelft.oopp.entities.TimeSlot;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.FoodRepository;
import nl.tudelft.oopp.repositories.FoodReservationRepository;
import nl.tudelft.oopp.repositories.RestaurantRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodReservationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodReservationRepository foodReservationRepository;

    /**
     * This function adds new food reservations.
     * @param foodId - the food ID
     * @param restaurantId - the restaurant ID
     * @param day - the date
     * @param userId - the user ID
     * @author Hidde Agterberg
     * @return a new food reservation
     */
    @PostMapping("/addFoodReservation/{foodId}/{restaurantId}/{day}/{startTime}/{endTime}/{userId}")
    @ResponseBody
    public FoodReservation addFoodReservation(@PathVariable (value = "foodId") int foodId,
                                              @PathVariable (value = "restaurantId") int restaurantId,
                                              @PathVariable (value = "day") Date day,
                                              @PathVariable (value = "startTime") Time startTime,
                                              @PathVariable (value = "endTime") Time endTime,
                                              @PathVariable (value = "userId") int userId) {

        Optional<Food> f = foodRepository.findById(foodId);
        Food food = f.get();

        Optional<Restaurant> r = restaurantRepository.findById(restaurantId);
        Restaurant restaurant = r.get();

        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        FoodReservation foodReservation = new FoodReservation(day);
        foodReservation.setUserFk(user);
        foodReservation.setFoodFk(food);
        foodReservation.setRestaurantFk(restaurant);
        foodReservation.setStartTime(startTime);
        foodReservation.setEndTime(endTime);

        System.out.println("Added a new food reservation");
        return foodReservationRepository.save(foodReservation);

    }

    /**
     * Get function that gets all food reservation in the DB.
     * @author Hidde Agterberg
     * @return all food reservations
     */
    @GetMapping("/getAllFoodReservations")
    @ResponseBody
    public List<FoodReservation> getAllFoodReservations() {
        return foodReservationRepository.getAllFoodReservations();
    }

    /**
     * Get function that gets a food reservation by id.
     * @param id - the id of the reservation
     * @author Hidde Agterberg
     * @return the food reservation
     */
    @GetMapping("/getFoodReservationById/{reservation_id}")
    @ResponseBody
    public List<FoodReservation> getFoodReservationById(@PathVariable(value = "reservation_id") int id) {
        return foodReservationRepository.getFoodReservationById(id);
    }

    /**
     * Get function that gets all food reservation by userId.
     * @param userId - the id of the user
     * @author Hidde Agterberg
     * @return all the food reservations of a user
     */
    @GetMapping("/getUsersFoodReservations/{userId}")
    @ResponseBody
    public List<String> getUsersFoodReservations(@PathVariable(value = "userId") int userId) {
        List<FoodReservation> foodReservationsAll = foodReservationRepository.findAll();
        List<String> reservationsForUser = new ArrayList<>();

        for (FoodReservation f : foodReservationsAll) {
            if (f.getUserFk().getUser_id() == (userId)) {
                reservationsForUser.add(f.toString());
            }
        }
        return reservationsForUser;
    }

    /**
     * This method deletes a food reservation made by a user.
     * @param foodReservationId - the id of the reservation that needs to be deleted
     */
    @DeleteMapping("deleteFoodReservation/{foodReservationId}")
    @ResponseBody
    public void deleteFoodReservation(@PathVariable(value = "foodReservationId") int foodReservationId) {
        try {
            Optional<FoodReservation> f = foodReservationRepository.findById(foodReservationId);
            FoodReservation foodReservation = f.get();
            foodReservation.setRestaurantFk(null);
            foodReservation.setUserFk(null);
            foodReservation.setFoodFk(null);
            foodReservationRepository.delete(foodReservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("the deletion has failed");
        }
    }

}
