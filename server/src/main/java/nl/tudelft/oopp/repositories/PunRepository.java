package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Pun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunRepository extends JpaRepository<Pun, String> {}
