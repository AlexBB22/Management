package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;



public class OverrideReservationPopUpSceneController implements Initializable {
    @FXML private Text yourUserName;
    @FXML private Text building;
    @FXML private Text room;
    @FXML private Text date;
    @FXML private Text time;
    @FXML private Text reservedBy;

    @FXML private Text resConfirmed;
    @FXML private Button cancelButton;
    private int reservationID;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yourUserName.setText(MainApp.user.getUserName());
        room.setText(RoomReservationSceneController.getRoomName());
        building.setText(RoomReservationSceneController.getBuildingName());
        date.setText(RoomReservationSceneController.getDay());
        time.setText(RoomReservationSceneController.getStartTime() + "-" + RoomReservationSceneController.getEndTime());
        reservedBy.setText(RoomReservationSceneController.getReservedBy() + " (" + RoomReservationSceneController.getReservedByRole() + ")");
        this.reservationID = RoomReservationSceneController.getReservationId();
    }

    @FXML
    void backBtnHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This method initiates the communication to override a reservation.
     */
    @FXML
    public void overrideReservation() {
        System.out.println("its working 2");
        /**
         * TODO: Make a method in ServerCommunication.java that makes a url to the DB to override a reservation
         * TODO: Call that method here, and then if successful, inform the user it was (resConfirmed.setText("...."))
         */
    }
}
