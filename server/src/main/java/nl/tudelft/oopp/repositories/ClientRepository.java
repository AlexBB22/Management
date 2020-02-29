package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    //SELECT * FROM client u WHERE u.user_name = "kanishdwivedi" and u.user_password = "password";
    @Query(value = "SELECT * FROM client c WHERE c.user_name=?1 AND c.user_password=?2", nativeQuery = true)
    List<ClientEntity> findByUserNameAndUserPassword(String userName, String userPassword);


}

