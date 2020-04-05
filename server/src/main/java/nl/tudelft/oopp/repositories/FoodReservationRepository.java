package nl.tudelft.oopp.repositories;

import java.util.List;

import nl.tudelft.oopp.entities.FoodReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodReservationRepository extends JpaRepository<FoodReservation, Integer> {

    /**
     * Query that returns all food reservations.
     * @author Hidde Agterberg
     * @return all food reservations
     */
    @Query(value = "SELECT * FROM foodreservation", nativeQuery = true)
    List<FoodReservation> getAllFoodReservations();

    /**
     * Query that returns a food reservations by id.
     * @param id - the id of the reservation
     * @author Hidde Agterberg
     * @return the food reservation
     */
    @Query(value = "SELECT * FROM foodreservation WHERE reservation_id = ?1", nativeQuery = true)
    List<FoodReservation> getFoodReservationById(int id);

    /**
     * Query that returns all food reservations of a user.
     * @param userId - the userId
     * @author Hidde Agterberg
     * @return all food reservations of a user
     */
    @Query(value = "SELECT * FROM foodreservation f NATURAL JOIN user u WHERE f.user = u.user_id AND u.user_id = ?1", nativeQuery = true)
    List<FoodReservation> getUsersFoodReservations(int userId);

}
