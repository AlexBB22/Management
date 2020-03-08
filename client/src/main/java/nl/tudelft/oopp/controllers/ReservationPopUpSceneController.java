package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

public class ReservationPopUpSceneController implements Initializable {
    @FXML private Text reservationUserName;
    @FXML private Text building;
    @FXML private Text room;
    @FXML private Text date;
    @FXML private Text time;

    @FXML private Text resConfirmed;
    @FXML private Button cancelButton;
    private int roomID;
    private String startTime;
    private String endTime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservationUserName.setText(MainApp.user.getUserName());
        room.setText(RoomReservationSceneController.getRoomName());
        building.setText(RoomReservationSceneController.getBuildingName());
        date.setText(RoomReservationSceneController.getDay());
        time.setText(RoomReservationSceneController.getStartTime() + "-" + RoomReservationSceneController.getEndTime());
        this.roomID = RoomReservationSceneController.getRoomID();
        this.startTime = RoomReservationSceneController.getStartTime();
        this.endTime = RoomReservationSceneController.getEndTime();
    }

    @FXML
    void backBtnHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void makeNewReservation() throws URISyntaxException {
        System.out.println(roomID + " - " + date.getText() + " - " + startTime);
        System.out.flush();
        int okCode = ServerCommunication.createRoomReservation(roomID, building.getText(), Date.valueOf(date.getText()),
               Time.valueOf(startTime), Time.valueOf(endTime));
        if(okCode == -1) {
            System.out.println("Looks like something went wrong! Try again!");
            return;
        } System.out.println("its working 1");
        /**
         * TODO: Make a method in ServerCommunication.java that makes a url to the DB to make a new reservation
         * TODO: Call that method here, and then if successful, inform the user it was (resConfirmed.setText("...."))
         */
    }
}
