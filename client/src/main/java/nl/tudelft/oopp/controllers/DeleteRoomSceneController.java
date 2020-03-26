package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.Room;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.Type;




public class DeleteRoomSceneController implements Initializable {

    @FXML private Text username;
    @FXML private ScrollPane roomScrollPane;
    @FXML private VBox vboxInScrollPane;

    private ArrayList<Room> roomList;

    private static int roomId;
    private static String roomName;
    private static int capacity;
    private static String buildingName;
    private static String typeName;

    public static int getRoomId() {
        return roomId;
    }

    public static String getRoomName() {
        return roomName;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static String getBuildingName() {
        return buildingName;
    }

    public static String getTypeName() {
        return typeName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        roomScrollPane.setContent(vboxInScrollPane);
        ArrayList<Room> rooms = null;

        try {
            rooms = ServerCommunication.getAllRooms();
            roomList = rooms;
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
                    roomId = r.getRoom_id();
                    roomName = r.getRoom_name();
                    capacity = r.getCapacity();
                    buildingName = r.getBuilding().getBuilding_Name();
                    typeName = r.getType().getName();
                    Parent root = FXMLLoader.load(getClass().getResource("/deleteRoomPopUpScene.fxml"));
                    Stage st = new Stage();
                    Scene sc = new Scene(root, 300, 400);
                    st.setScene(sc);
                    st.show();
                } catch (IOException e) {
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

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
    }
}
