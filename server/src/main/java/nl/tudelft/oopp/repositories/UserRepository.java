package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user u WHERE u.user_name=?1 AND u.user_password=?2", nativeQuery = true)
    List<User> findByUserNameAndUserPassword(String user_name, String user_password);
}
