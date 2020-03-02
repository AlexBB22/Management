package nl.tudelft.oopp.controllers;

import nl.tudelft.oopp.entities.Role;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.RoleRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("addUser/{roleName}")
    @ResponseBody
    public User addUser(@PathVariable String roleName, @RequestBody User user) {
        User newuser = user;
        List<Role> roles = roleRepository.findByRoleName(roleName);
        Role userRole = roles.get(0);
        newuser.setRole(userRole);


        System.out.println("Added this user to the DB: " + user.toString());

        return userRepository.save(newuser);
    }

    @GetMapping("getListOfUsers")
    @ResponseBody
    public List<User> getUsers() {
        return userRepository.findAll();
    }



    @GetMapping("identifyMe/{credentials}")
    @ResponseBody
    public boolean authenticate(@PathVariable String credentials) {
        String[] userInputs = credentials.split(":");
        String givenUsername = userInputs[0];
        String givenPassword = userInputs[1];
        //query DB to see if a user w/givenUsername and password exists
        List<User> users = userRepository.findByUserNameAndUserPassword(givenUsername, givenPassword);
        for (User u: users) {
            System.out.println(u.toString());
        }
        if (users.size() == 0) {
            return false;
        }
        return true;
    }
}
