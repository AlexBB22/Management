package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

import java.net.URISyntaxException;

public class BikeController {

    @FXML
    private TextField colorField;

    public void colorInputed() throws URISyntaxException {
        String color = colorField.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sorted bikes");
        alert.setHeaderText(null);
        alert.setContentText(ServerCommunication.getBikes(color));
        alert.showAndWait();
    }
}
