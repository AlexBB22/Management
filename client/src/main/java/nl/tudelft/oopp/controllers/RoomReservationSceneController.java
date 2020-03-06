package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.CompactNumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.Room;
import nl.tudelft.oopp.communication.ServerCommunication;



public class RoomReservationSceneController implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> buildingComboBox;
    @FXML private ComboBox<String> timeFromComboBox;
    @FXML private ComboBox<String> timeToComboBox;
    @FXML private ComboBox<String> roomTypeComboBox;
    @FXML private VBox roomList;
    @FXML private Text username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        // TODO: populate combo boxes and show available rooms
        ObservableList<String> times = FXCollections.observableArrayList(
                "08:45", "10:45", "12:45", "13:45", "15:45", "17:45"
        );
        timeFromComboBox.getItems().addAll(times);
        //timeToComboBox.getItems().addAll(times);

        ArrayList<Building> buildings = null;
        try {
            buildings = ServerCommunication.getBuildings();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Building b : buildings) {
            buildingComboBox.getItems().add(b.getBuilding_Name());
        }
    }

    /**.
     * Search button handler
     * @param actionEvent the event that happens
     * @throws URISyntaxException throws exception when wrong syntax is given
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent)
            throws URISyntaxException, IOException {

        // Clear vbox before adding items
        roomList.getChildren().clear();

        //String[] rooms = {"rooms akdmkwadawdjlawjdjakwd", "wdawdawdawdwadawda", "awjdawjd"};

        ArrayList<Room> rooms = ServerCommunication.getRooms(datePicker.getValue(),
                buildingComboBox.getValue(), timeFromComboBox.getValue(), timeToComboBox.getValue(),
                roomTypeComboBox.getValue());

        for (Room room : rooms) {
            Text roomName = new Text(room.getRoom_name());
            Button reserveBtn = new Button("Reserve");
            reserveBtn.setAlignment(Pos.TOP_RIGHT);
            reserveBtn.setOnAction(event -> {
                try {
                    reservePopUp(room.getBuilding().getBuilding_Name(),
                            room.getRoom_name(),
                            "date", // TODO: datePicker.getValue().toString(),
                            timeFromComboBox.getValue() + " - " + timeToComboBox.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            HBox container = new HBox(roomName, reserveBtn);
            container.setPadding(new Insets(0,0,5,0));
            roomList.getChildren().add(container);
        }
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/accountScene.fxml", "Account settings");
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

    //TODO: show info in popup

    /**
     * A method to create a popup for a new room reservation.
     * @param building - the name of the building
     * @param room - the rooms to be shown
     * @param date - the dates
     * @param time - the time
     * @throws IOException - exception thrown when file not found
     */
    public void reservePopUp(String building, String room, String date, String time)
                                throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/reservationPopUpScene.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }

}
