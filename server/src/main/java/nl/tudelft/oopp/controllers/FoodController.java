package nl.tudelft.oopp.controllers;

import nl.tudelft.oopp.entities.Food;
import nl.tudelft.oopp.entities.Menu;
import nl.tudelft.oopp.entities.Restaurant;
import nl.tudelft.oopp.repositories.FoodRepository;
import nl.tudelft.oopp.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Add food post function.
     * @param id - food id
     * @param price - price of the food
     * @return add the food to the db
     */
    @PostMapping("/addFood/{foodId}/{price}")
    @ResponseBody
    public Food addFood(@PathVariable(value = "foodId") String id,
                        @PathVariable(value = "price") int price) {

        Food food = new Food(id, price);

        System.out.println("Added a new food");
        return foodRepository.save(food);

    }

    /**
     * Get function that gets all food in the DB.
     * @author Hidde Agterberg
     * @return all food
     */
    @GetMapping("/getAllFood")
    @ResponseBody
    public List<Food> getAllFood() {
        return foodRepository.getAllFood();
    }

    /**
     * Get function that gets all food in the DB for a given restaurant.
     * @author Niels Tomassen
     * @return list of food
     */
    @GetMapping("/getAllFoodForRestaurant/{resId}")
    @ResponseBody
    public List<Food> getAllFoodForRestaurant(@PathVariable(value = "resId") int resId) {
        Optional<Restaurant> r = restaurantRepository.findById(resId);
        Restaurant restaurant = r.get();

        Menu menu = restaurant.getMenu();

        List<Food> foods = menu.getFoods();
        return foods;
    }
}

