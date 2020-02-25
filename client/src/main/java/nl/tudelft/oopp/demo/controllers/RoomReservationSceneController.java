package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomReservationSceneController implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> buildingComboBox;
    @FXML private ComboBox<String> timeslotComboBox;
    @FXML private ComboBox<String> roomTypeComboBox;

    @FXML private VBox roomList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: populate combo boxes and show available rooms

    }

    public void searchButtonHandler(ActionEvent actionEvent) {

    }

    public void accountButtonHandler(MouseEvent mouseEvent) {
    }
}
