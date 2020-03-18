package nl.tudelft.oopp.controllers;
import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.Room;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.Type;


public class AddRoomSceneController implements Initializable {

    @FXML private TextField capacityTextField;
    @FXML private TextField roomNameTextField;
    @FXML private ComboBox<String> buildingComboBox;
    @FXML private ComboBox<String> typeNameComboBox;
    @FXML private ScrollPane roomScrollPane;
    @FXML private Text username;
    @FXML private VBox VBoxInSP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        ArrayList<Room> rooms = null;
        roomScrollPane.setContent(VBoxInSP);
        try {
            rooms = ServerCommunication.getAllRooms();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        GridPane gridPane = new GridPane();
        for (Room r : rooms) {

            gridPane.add(new Text(r.getRoom_name()), 0, i);
            i = i + 1;
            gridPane.add(new Text("capacity: " + r.getCapacity() + " Building: " + r.getBuilding()), 0, i);
            i = i + 1;
            Type or = r.getType();
            gridPane.add(new Text("Clicker: " + Boolean.toString(or.isClicker()) + " PowerOutlets: " + Boolean.toString(or.isPowerOutlets()) + " TV: " + Boolean.toString(or.isTv())), 0, i);
            i = i + 1;
            gridPane.add(new Text("Whiteboard: " + Boolean.toString(or.isWhiteBoard())), 0, i);
            i = i + 1;

        }
        roomScrollPane.setContent(gridPane);
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
    }
}
