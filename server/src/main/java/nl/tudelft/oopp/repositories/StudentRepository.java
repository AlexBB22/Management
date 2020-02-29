package nl.tudelft.oopp.repositories;

import nl.tudelft.oopp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByAgeGreaterThan(int Age);
}
