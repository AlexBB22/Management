package nl.tudelft.oopp.demo.controllers;
import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.demo.entities.BikeEntity;
import nl.tudelft.oopp.demo.repositories.BikeRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

@Controller
public class BikeController {

    @Autowired
    private BikeRepository bikerep;

    @GetMapping("bike/{givenColor}")
    @ResponseBody
    public String sortBikesByColor(@PathVariable String givenColor) {
        String color = givenColor;


        List<BikeEntity> list = bikerep.findBikeByColor(color);
        if(list.size() == 0) {
            return "No bikes with that specific color found!";
        }
        String res = "";
        for(int i = 0; i < list.size(); i++) {
            res += list.get(i).toString();
            if(i < list.size() -1) {
                res += "\n";
            }
        }
        return res;
    }

}
