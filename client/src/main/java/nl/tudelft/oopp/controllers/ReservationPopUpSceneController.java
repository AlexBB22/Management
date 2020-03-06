package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ReservationPopUpSceneController {
    @FXML private Text building;
    @FXML private Text room;
    @FXML private Text date;
    @FXML private Text time;
    @FXML private Text resConfirmed;

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/roomReservationSceneController.fxml", "Reserve a room");
    }
}
