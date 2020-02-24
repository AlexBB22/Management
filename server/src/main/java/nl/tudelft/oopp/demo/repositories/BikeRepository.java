package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<BikeEntity, Integer> {

    //SELECT * FROM bike b WHERE b.color = "blue";
    @Query(value = "SELECT * FROM bike b WHERE b.color=?1", nativeQuery = true)
    List<BikeEntity> findBikeByColor(String color);


}

