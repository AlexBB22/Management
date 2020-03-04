package nl.tudelft.oopp.controllers;

import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.entities.Role;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.RoleRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * This end point creates a new user using the entity POJO objects using information received from HTTP Request body.
     * @param roleId - The id of the Role this user will be assigned to (eg. id = 4, aka new user is Admin)
     * @param user - The user which is to be added to the database
     * @return - int. If -1, the userName already exists in the database, else returns the ID of the new user.
     */
    @RequestMapping(value = "addUser/{roleId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int addUser(@PathVariable(value = "roleId") int roleId, @RequestBody User user) {
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

    /**
     * This endPoint authorises a user based on the credentials given in the HTTP Request URL.
     * @param userName - the userName that the user has given when trying to log in
     * @param hashedPassword - the password that the user has given when trying to log in
     * @return - The User entity that is stored in the Database is returned if it exists with the given username and password, else null
     */
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
