package nl.tudelft.oopp.demo.controllers;


import java.util.List;

import nl.tudelft.oopp.demo.entities.Student;
import nl.tudelft.oopp.demo.repositories.StudentRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

//@Controller
//@RequestMapping(path = "/getstudents")

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getstudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
