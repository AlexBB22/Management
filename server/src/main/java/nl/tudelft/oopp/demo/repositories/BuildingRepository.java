package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, String>{

    //get all buildings
    @Query(value = "SELECT building.name FROM building", nativeQuery = true)
    List<String> getAllBuildingNames();

//    @Query(value = "SELECT * FROM building b WHERE b.name=?1", nativeQuery = true)
//    List<Building> findByBuildingName(String buildingName);
}
