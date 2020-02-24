package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.ClientEntity;
import nl.tudelft.oopp.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

public class RestaurantController {

    @Autowired
    private RestaurantRepository resrep;

    @GetMapping("getRestaurantsByID/{id}")
    @ResponseBody
    public Optional<ClientEntity> getRestaurantByID(@PathVariable String id) {
        int newID=Integer.parseInt(id);
        return resrep.findById(newID);
    }

}
