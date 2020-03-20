package nl.tudelft.oopp.controllers;

import nl.tudelft.oopp.entities.Todo;
import nl.tudelft.oopp.entities.User;
import nl.tudelft.oopp.repositories.TodoRepository;
import nl.tudelft.oopp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories("nl.tudelft.oopp.repositories")

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("getAllTodos/{userID}")
    @ResponseBody
    public List<Todo> getAllTodos(@PathVariable int userID) {
        return todoRepository.getTodoByUserFk_UserIdEquals(userID);
    }

    @PostMapping("addNewTodo/{userID}/{day}")
    @ResponseBody
    public boolean addNewTodo(@PathVariable (value = "userID") int userID, @PathVariable (value = "day") Date day,
                              @RequestBody Todo todo) {
        //get user by given ID
        Optional<User> u = userRepository.findById(userID);
        if (u.isEmpty()) {
            return false;
        }
        User user = u.get();

        //use helper methods to make sure both sides of relationship have updated their attributes
        todo.setUserFk(user);
        todo.setDay(day);
        System.out.println("Added a new todo: " + todo.toString());
        todoRepository.save(todo);

        return true;
    }


}
