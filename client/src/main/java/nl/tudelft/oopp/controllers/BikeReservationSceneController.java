package nl.tudelft.oopp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    @FXML
    private DatePicker datePickerBike;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToLogin(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");

    }

    /**
     * You won't see the buildings where you can't reserve buildings!
     * @param actionEvent It shows all the posible buildings where you are able to reserve a bike
     */
    public void getBuildings(ActionEvent actionEvent) throws IOException, URISyntaxException {
        List<Building> listOfBuildings = ServerCommunication.getBuildings();
        BuildingList.getChildren().clear();
        for (Building building: listOfBuildings) {
            if (ServerCommunication.getNumberOfAvailableBikes(building.getBuilding_Name(), datePickerBike.getValue())>0) {
                Text buildingName = new Text(building.getBuilding_Name());
                Button reserveButton = new Button("Reserve");
                reserveButton.setAlignment(Pos.TOP_RIGHT);
            /*reserveButton.setOnAction(event -> {
                try {

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });*/
                HBox container = new HBox(buildingName, reserveButton);
                BuildingList.getChildren().add(container);
            }
        }
    }
}
