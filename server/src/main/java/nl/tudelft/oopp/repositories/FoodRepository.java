package nl.tudelft.oopp.repositories;

import java.util.List;

import nl.tudelft.oopp.entities.Food;
import nl.tudelft.oopp.entities.FoodReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    /**
     * Query that returns all food.
     * @author Hidde Agterberg
     * @return all food
     */
    @Query(value = "SELECT * FROM food", nativeQuery = true)
    List<Food> getAllFood();

}
