package nl.tudelft.oopp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.ServerCommunication;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static nl.tudelft.oopp.MainApp.switchScene;

public class BikeReservationSceneController implements Initializable {

    @FXML
    private VBox BuildingList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToLogin(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");

    }

    public void getBuildings(ActionEvent actionEvent) throws IOException, URISyntaxException {
        List<Building> listOfBuildings = ServerCommunication.getBuildings();
        for (int a = 0; a < listOfBuildings.size(); a++){
            String Building = listOfBuildings.get(a).toString();
            Text someText = new Text(Building);
            BuildingList.getChildren().add(someText);
        }
    }
}
