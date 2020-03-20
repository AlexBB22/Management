package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.Room;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.Type;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteRoomSceneController implements Initializable {

    @FXML private Text username;
    @FXML private ScrollPane roomScrollPane;
    @FXML private VBox VboxInScrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        roomScrollPane.setContent(VboxInScrollPane);
        ArrayList<Room> rooms = null;

        try {
            rooms = ServerCommunication.getAllRooms();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        int i = 0;
        GridPane gridPane = new GridPane();
        for (Room r : rooms) {
            Button deleteBtn = new Button("Delete");
            deleteBtn.setAlignment(Pos.CENTER_LEFT);
            deleteBtn.setOnAction(event -> {
                try {
                    ServerCommunication.deleteRoom(r.getRoom_id());
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setContentText("Room deleted successfully.");
                    warning.show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            gridPane.add(new Text(r.getRoom_name()), 0, i);
            i = i + 1;
            gridPane.add(new Text("capacity: " + r.getCapacity() + " Building: " + r.getBuilding().getBuilding_Name()), 0, i);
            i = i + 1;
            Type or = r.getType();
            gridPane.add(new Text("Clicker: " + Boolean.toString(or.isClicker()) + " PowerOutlets: " + Boolean.toString(or.isPowerOutlets()) + " TV: " + Boolean.toString(or.isTv())), 0, i);
            i = i + 1;
            gridPane.add(new Text("Whiteboard: " + Boolean.toString(or.isWhiteBoard())), 0, i);
            gridPane.add(deleteBtn, 1, i);
            i = i + 1;
        }
        roomScrollPane.setContent(gridPane);

    }
}
