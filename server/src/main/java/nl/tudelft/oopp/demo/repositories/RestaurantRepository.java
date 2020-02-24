package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.ClientEntity;
import nl.tudelft.oopp.demo.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    //SELECT * FROM projects_OOPPGroup4.restaurant r WHERE r.building='ewi';
    @Query(value = "SELECT * FROM projects_OOPPGroup4.restaurant r WHERE r.building=?1;", nativeQuery = true)
    List<RestaurantEntity> findByBuilding(String building);
}
