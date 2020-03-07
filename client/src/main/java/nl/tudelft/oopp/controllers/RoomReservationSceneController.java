package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
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
import nl.tudelft.oopp.communication.OverridableRoom;
import nl.tudelft.oopp.communication.Room;
import nl.tudelft.oopp.communication.ServerCommunication;



public class RoomReservationSceneController implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> buildingComboBox;
    @FXML private ComboBox<String> timeSlotComboBox;
    @FXML private ComboBox<String> roomTypeComboBox;
    @FXML private VBox roomList;
    @FXML private Text username;
    @FXML private Text selectBuildingMessage;

    private ArrayList<Building> buildingList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        // TODO: populate combo boxes and show available rooms
        ArrayList<Building> buildings = null;
        try {
            buildings = ServerCommunication.getBuildings();
            buildingList = buildings;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Building b : buildings) {
            buildingComboBox.getItems().add(b.getBuilding_Name());
        }
        //hide timeslot combobox
        timeSlotComboBox.setDisable(true);
        selectBuildingMessage.setText("Select a building first please");
    }

    /**
     * Whenever a building is selected from the dropdown menu
     * depending on the building, we give different start and end times.
     * @param actionEvent - event that describes a selection of a value from the buildingComboBox
     */
    @FXML
    public void getBuildingTimes(ActionEvent actionEvent) {
        //Setting timSlotComboBox to have correct values
        String buildingName = buildingComboBox.getValue();
        Time startTime = null;
        Time endTime = null;
        for (Building b: buildingList) {
            String buName = b.getBuilding_Name();
            if (buName.equals(buildingName)) {
                startTime = b.getOpening();
                endTime = b.getClosing();
            }
        }
        startTime.toString();
        endTime.toString();

        ObservableList<String> times = FXCollections.observableArrayList();
        //add specific start time if its not the regular one
        if (startTime.equals("08:45:00") == false) {
            String t1 = startTime + "-" + "08:45:00";
            times.add(t1);
        }
        //add regular times
        String t2 = "08:45:00-10:45:00";
        String t3 = "10:45:00-12:45:00";
        String t4 = "13:45:00-15:45:00";
        String t5 = "15:45:00-17:45:00";
        times.addAll(t2, t3, t4, t5);

        //add specific end time if its not the regular one
        if (endTime.equals("17:45:00") == false) {
            String t6 = "17:45:00" + "-" + endTime;
            times.add(t6);
        }
        //first clear out items in combobox then add new set
        timeSlotComboBox.getItems().clear();
        timeSlotComboBox.getItems().addAll(times);

        timeSlotComboBox.setDisable(false);
        selectBuildingMessage.setText("");
    }


    /**.
     * Search button handler.
     * @param actionEvent the event that happens
     * @throws URISyntaxException throws exception when wrong syntax is given
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent)
            throws URISyntaxException, IOException {

        // Clear vbox before adding items
        roomList.getChildren().clear();

        //Getting the start and end time the user selected
        String[] timeSlot = timeSlotComboBox.getValue().split("-");
        String starttime = timeSlot[0];
        String endtime = timeSlot[1];

        ArrayList<OverridableRoom> overridableRooms = ServerCommunication.getOverridableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime, 1);

        for (OverridableRoom or: overridableRooms) {
            Text roomName = new Text(or.getRoomName());
            Button reserveBtn = new Button("Override This Reservation");
            reserveBtn.setAlignment(Pos.TOP_RIGHT);
            reserveBtn.setOnAction(event -> {
                try {
                    reservePopUp(or.getBuildingName(),
                            or.getRoomName(),
                            "date", // TODO: datePicker.getValue().toString(),
                            timeSlotComboBox.getValue());
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
