package nl.tudelft.oopp.repositories;

import java.util.List;

import nl.tudelft.oopp.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 * This has a query for retreiving all the restaurants from a building.
 * @author Alex
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query(value = "SELECT * FROM restaurant WHERE building_fk = ?1", nativeQuery = true)
    List<Restaurant> getRestaurantsByBuildingName(String buildingName);

    @Query(value = "SELECT * FROM restaurant", nativeQuery = true)
    List<Restaurant> getAllRestaurants();
}
