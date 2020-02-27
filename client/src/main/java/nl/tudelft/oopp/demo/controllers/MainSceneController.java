package nl.tudelft.oopp.demo.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSceneController {

    @FXML private TextField buildingName;
    /**
     * Handles clicking the button.
     */
    public void buttonClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quote for you");
        alert.setHeaderText(null);
        alert.setContentText(ServerCommunication.getQuote());
        alert.showAndWait();
    }
    public void buttonClicked2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Best pun of the day");
        alert.setHeaderText(null);
        alert.setContentText(ServerCommunication.getPun());
        alert.showAndWait();
    }

    @FXML
    public void buildingclicklist() {
        String BuildingName = buildingName.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Building names:");
        alert.setHeaderText(null);
        alert.setContentText(ServerCommunication.getBuildingNames(BuildingName));
        alert.showAndWait();
    }

    @FXML
    public void bikeButtonClicked(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sortByColor.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
