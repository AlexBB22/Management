package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT * FROM role r WHERE r.role_name = ?1", nativeQuery = true)
    List<Role> findByRoleName(String roleName);

}
