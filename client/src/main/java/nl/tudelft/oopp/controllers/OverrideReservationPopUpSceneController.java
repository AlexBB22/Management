package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
    public void overrideReservation() throws URISyntaxException, IOException {
        int okCode = ServerCommunication.overrideRoomReservation(this.reservationID, MainApp.user.getUserId());
        if(okCode == -1) {
            resConfirmed.setText("Looks like something went wrong! Try again!");
            return;
        }
        resConfirmed.setText("Reservation set.");
        backBtnHandler();
        MainSceneController.setStatus(1);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/mainScene.fxml"));
        Parent root = loader.load();
        MainView.getPrimaryStage().setScene(new Scene(root));

        /**
         * TODO: Make a method in ServerCommunication.java that makes a url to the DB to override a reservation
         * TODO: Call that method here, and then if successful, inform the user it was (resConfirmed.setText("...."))
         */

    }
}
