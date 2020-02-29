package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {}
