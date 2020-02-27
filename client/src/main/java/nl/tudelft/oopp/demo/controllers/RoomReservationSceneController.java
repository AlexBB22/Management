package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

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

    /**
     * Search button handler
     * @param actionEvent
     * @throws URISyntaxException
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent) throws URISyntaxException {
        // Clear vbox before adding items
        //roomList.getChildren().clear();

        String[] rooms = {"rooms akdmkwadawdjlawjdjakwd", "wdawdawdawdwadawda", "awjdawjd"};

        /*String[] rooms = ServerCommunication.getRooms(datePicker.getValue(),
                buildingComboBox.getValue(), timeslotComboBox.getValue(),
                roomTypeComboBox.getValue());*/

        for (String room : rooms) {
            Text roomName = new Text(room);
            Button reserveBtn = new Button("Reserve");
            reserveBtn.setOnAction(event -> {
                try {
                    reservePopUp(buildingComboBox.getValue(),
                            room,
                            "date", // TODO: datePicker.getValue().toString(),
                            timeslotComboBox.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            HBox container = new HBox(roomName, reserveBtn);
            roomList.getChildren().add(container);
        }
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) {
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) {
    }

    //TODO: show info in popup
    public void reservePopUp(String building, String room, String date, String time)
                                throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/reservationPopUpScene.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }

}
