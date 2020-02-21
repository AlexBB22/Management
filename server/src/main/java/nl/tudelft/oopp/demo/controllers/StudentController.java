package nl.tudelft.oopp.demo.controllers;
import java.util.List;
import java.util.Optional;
import nl.tudelft.oopp.demo.entities.Student;
import nl.tudelft.oopp.demo.repositories.StudentRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

//@RequestMapping(path = "/getstudents")

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentrep;


    @GetMapping("getstudents")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentrep.findAll();
    }

    @GetMapping("getStudentsByID/{id}")
    @ResponseBody
    public Optional<Student> getStudentByID(@PathVariable String id) {
        int studentID = Integer.parseInt(id);
        return studentrep.findById(studentID);
    }

    @GetMapping("getStudentsByAge/{age}")
    @ResponseBody
    public List<Student> getStudentByAge(@PathVariable String age) {
        int studentage = Integer.parseInt(age);
        return studentrep.findByAgeGreaterThan(studentage);
    }



}
