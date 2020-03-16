package nl.tudelft.oopp.repositories;

import java.util.Optional;

import nl.tudelft.oopp.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Menu repo.
 */
@Repository
public interface MenuRepository  extends JpaRepository<Menu, Integer> {
    @Query(value = "SELECT * FROM menu WHERE menu_id = ?1", nativeQuery = true)
    Optional<Menu> findMenuById(Integer menuId);
}
