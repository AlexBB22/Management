
/*package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import java.util.Optional;

import nl.tudelft.oopp.demo.entities.Building;
<<<<<<< HEAD
//import nl.tudelft.oopp.demo.entities.ClientEntity;
//import nl.tudelft.oopp.demo.entities.Student;
import nl.tudelft.oopp.demo.repositories.BuildingRepository;
//import nl.tudelft.oopp.demo.repositories.ClientRepository;
//import nl.tudelft.oopp.demo.repositories.StudentRepository;
=======
import nl.tudelft.oopp.demo.repositories.BuildingRepository;
>>>>>>> 7b72ba24a04a7c9744b233372080a38b6d9d5291
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")

@Controller
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("buildings/All")
    @ResponseBody
    public List<Building>  getAllBuildings(){
        return buildingRepository.findAll();
    }

    @GetMapping("buildings/{buildingName}")
    @ResponseBody
    public Building findByBuildingName(@PathVariable String buildingName) {
        try {
            List<Building> allBuildings = buildingRepository.findAll();
            for (int i = 0; i < allBuildings.size(); i++) {
                if (allBuildings.get(i).getBuilding_Name().equals(buildingName)) {
                    return allBuildings.get(i);
                }
            }
            throw new IllegalArgumentException("this building does not exist");
        }
        catch(Exception e){
            throw new IllegalArgumentException("this input does not exist");
        }
    }
}
 */
