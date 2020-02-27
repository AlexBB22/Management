//package nl.tudelft.oopp.demo.controllers;
//
//import java.util.List;
//import java.util.Optional;
//
//import nl.tudelft.oopp.demo.entities.Building;
//import nl.tudelft.oopp.demo.repositories.BuildingRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@EnableJpaRepositories("nl.tudelft.oopp.demo.repositories")
//
//@Controller
//public class BuildingController {
//
//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @GetMapping("buildings/All")
//    @ResponseBody
//    public List<Building>  getAllBuildings(){
//        return buildingRepository.findAll();
//    }
//
//    @GetMapping("buildings/{buildingName}")
//    @ResponseBody
//    public Building findByBuildingName(@PathVariable String buildingName) {
//        try {
//            List<Building> allBuildings = buildingRepository.findAll();
//            for (int i = 0; i < allBuildings.size(); i++) {
//                if (allBuildings.get(i).getAllBuildingNames().equals(buildingName)) {
//                    return allBuildings.get(i);
//                }
//            }
//            throw new IllegalArgumentException("this building does not exist");
//        }
//        catch(Exception e){
//            throw new IllegalArgumentException("this input does not exist");
//        }
//    }
//}
