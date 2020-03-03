package nl.tudelft.oopp.controllers;

import nl.tudelft.oopp.entities.Role;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.RoleRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //@PostMapping("addUser/{roleId}")
    @RequestMapping(value = "addUser/{roleId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int addUser(@PathVariable (value = "roleId") int roleId, @RequestBody User user) {
        User newuser = user;
        Optional<Role> r = roleRepository.findById(roleId);
        Role userRole = r.get();

        //1st check if a user already exists with the same username in the DB, if so we return -1
        String newUserName = newuser.getUser_name();
        List<User> users = userRepository.findByUser_name(newUserName);
        if (users.size() >= 1) {
            return -1;
        }

        //At this point, we know that the userName is unique and we can continue to save into the DB
        newuser.setRole(userRole);

        System.out.println("Added this user to the DB: " + user.toString());
        //Save the new user to the DB and then also save its copy here
        User userInDB = userRepository.save(newuser);

        //return the id of the new user back to the client
        return userInDB.getUser_id();
    }

    @GetMapping("getListOfUsers")
    @ResponseBody
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("identifyMe/{userName}/{hashedPassword}")
    @ResponseBody
    public User authenticate(@PathVariable (value = "userName") String userName, @PathVariable (value = "hashedPassword") String hashedPassword) {
        //query DB to see if a user w/givenUsername and password exists
        List<User> users = userRepository.findByUserNameAndUserPassword(userName, hashedPassword);
        for (User u: users) {
            System.out.println(u.toString());
        }
        //If no user exists with given username and password, then we return null
        if (users.size() == 0) {
            return null;
        }
        //Return user back to the client, which can then be used by client side code to make further user role specific requests
        return users.get(0);
    }
}
