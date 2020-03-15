package nl.tudelft.oopp.controllers;

import java.sql.Date;
import java.util.Optional;

import nl.tudelft.oopp.entities.Food;
import nl.tudelft.oopp.entities.FoodReservation;
import nl.tudelft.oopp.entities.Restaurant;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.FoodRepository;
import nl.tudelft.oopp.repositories.FoodReservationRepository;
import nl.tudelft.oopp.repositories.RestaurantRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @PostMapping("/addFoodReservation/{foodId}/{restaurantId}/{day}/{userId}")
    @ResponseBody
    public FoodReservation addFoodReservation(@PathVariable(value = "foodId") String foodId,
                                              @PathVariable (value = "restaurantId") int restaurantId,
                                              @PathVariable (value = "day") Date day,
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
        // user.addBikeReservation(bikeReservation);

        System.out.println("Added a new bike reservation");
        return foodReservationRepository.save(foodReservation);

    }

}
