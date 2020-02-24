package nl.tudelft.oopp.demo.controllers;

import javafx.scene.control.Alert;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

public class MainSceneController {

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
}
