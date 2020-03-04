package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
    @Query(value = "SELECT Distinct * FROM bike WHERE building_name=?1", nativeQuery = true)
    List<Bike> getBikesByBuildingName(String buildingName);
}
