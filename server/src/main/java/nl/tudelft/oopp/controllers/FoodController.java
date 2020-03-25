package nl.tudelft.oopp.controllers;

import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.Food;
import nl.tudelft.oopp.entities.FoodReservation;
import nl.tudelft.oopp.entities.Menu;
import nl.tudelft.oopp.repositories.FoodRepository;
import nl.tudelft.oopp.repositories.MenuRepository;
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

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Add food post function.
     * @param name - food name
     * @param price - price of the food
     * @return add the food to the db
     */
    @PostMapping("/addFood/{name}/{price}")
    @ResponseBody
    public Food addFood(@PathVariable(value = "name") String name,
                        @PathVariable(value = "price") int price) {

        Food food = new Food(name, price);

        System.out.println("Added a new food");
        return foodRepository.save(food);

    }

    @PostMapping("/addFoodToMenu/{foodId}/{menuId}")
    @ResponseBody
    public Menu addFoodToMenu(@PathVariable(value = "foodId") int foodId,
                              @PathVariable(value = "menuId") int menuId) {

        Optional<Food> food = foodRepository.findById(foodId);
        Optional<Menu> menu = menuRepository.findById(menuId);
        menu.get().getFoods().add(food.get());

        return menuRepository.save(menu.get());
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
