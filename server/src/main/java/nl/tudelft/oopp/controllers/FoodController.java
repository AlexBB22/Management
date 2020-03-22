package nl.tudelft.oopp.controllers;

import java.util.List;

import nl.tudelft.oopp.entities.Food;
import nl.tudelft.oopp.entities.FoodReservation;
import nl.tudelft.oopp.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

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
}
