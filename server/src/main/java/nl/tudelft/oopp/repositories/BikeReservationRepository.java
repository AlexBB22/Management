package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.BikeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeReservationRepository extends JpaRepository<BikeReservation, Integer> {
}
