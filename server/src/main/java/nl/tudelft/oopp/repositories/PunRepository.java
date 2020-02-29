package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.Pun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunRepository extends JpaRepository<Pun, String> {}
