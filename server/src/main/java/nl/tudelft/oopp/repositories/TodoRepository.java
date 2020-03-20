package nl.tudelft.oopp.repositories;

import java.util.List;
import nl.tudelft.oopp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> getTodoByUserFk_UserIdEquals(int userID);
}
