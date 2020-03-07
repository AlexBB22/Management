package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReservationPopUpSceneController {
    @FXML private Text building;
    @FXML private Text room;
    @FXML private Text date;
    @FXML private Text time;
    @FXML private Text resConfirmed;
    @FXML private Button cancelButton;

    public void setBuilding(Text building) {
        this.building = building;
    }

    public void setRoom(Text room) {
        this.room = room;
    }

    public void setDate(Text date) {
        this.date = date;
    }

    public void setTime(Text time) {
        this.time = time;
    }

    @FXML
    void backBtnHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
