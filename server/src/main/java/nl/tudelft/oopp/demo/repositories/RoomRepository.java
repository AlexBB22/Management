package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Role;
import nl.tudelft.oopp.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT * FROM room WHERE building_name = ?1", nativeQuery = true)
    List<Room> testing(String buildingName);
}
