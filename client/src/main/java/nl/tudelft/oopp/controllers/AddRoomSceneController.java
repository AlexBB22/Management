package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.Room;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.Type;


public class AddRoomSceneController implements Initializable {

    //This arrayLists just saves all the buildings and types from the query made during initialisation
    private ArrayList<Building> buildingList;
    private ArrayList<Type> typeArrayList;

    @FXML private TextField capacityTextField;
    @FXML private TextField roomNameTextField;
    @FXML private ComboBox<String> buildingComboBox;
    @FXML private ComboBox<String> typeNameComboBox;
    @FXML private ScrollPane roomScrollPane;
    @FXML private Text username;
    @FXML private VBox vboxInSP;
    @FXML private Text confirmationText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        ArrayList<Room> rooms = null;
        roomScrollPane.setContent(vboxInSP);

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

            Image image = getCorrectImage(r.getType().getName());
            ImageView imV = new ImageView();
            imV.setImage(image);
            imV.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            imV.setFitWidth(180);
            imV.setFitHeight(135);
            gridPane.add(imV, 0, i);

            Text roomName = new Text(r.getRoom_name());
            roomName.setFont(Font.font(20));

            gridPane.add(roomName, 1, i);
            i = i + 1;
            gridPane.add(new Text("capacity: " + r.getCapacity() + " Building: " + r.getBuilding().getBuilding_Name()), 0, i);
            i = i + 1;
            Type or = r.getType();
            gridPane.add(new Text("Clicker: " + or.isClicker() + " PowerOutlets: " + or.isPowerOutlets() + " TV: " + or.isTv()), 0, i);
            i = i + 1;
            gridPane.add(new Text("Whiteboard: " + or.isWhiteBoard()), 0, i);
            i = i + 1;
            gridPane.add(new Text(""), 0, i);
            i = i + 1;

        }
        roomScrollPane.setContent(gridPane);

        ArrayList<Type> types = null;
        ArrayList<Building> buildings = null;
        try {
            buildings = ServerCommunication.getBuildings();
            buildingList = buildings;
            types = ServerCommunication.getTypes();
            typeArrayList = types;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Building b : buildings) {
            buildingComboBox.getItems().add(b.getBuilding_Name());
        }
        for (Type t : types) {
            typeNameComboBox.getItems().add(t.getName());
        }
    }

    /**
     * This method creates an JavaFX Image object based on the roomType given.
     * @author Kanish Dwivedi
     * @param roomType - the name of the roomType for which the coressponding image is to be loaded
     * @return - a new Image object that represents the roomType given
     */
    public Image getCorrectImage(String roomType) {
        Image resImg = null;
        if (roomType.equals("StudyRoom")) {
            resImg = new Image("images/studyRoom.jpg");
        }
        if (roomType.equals("ProjectRoom")) {
            resImg = new Image("images/projectRoom.jpg");
        }
        if (roomType.equals("LectureHall")) {
            resImg = new Image("images/lectureHall.jpg");
        }
        if (roomType.equals("StudyHall")) {
            resImg = new Image("images/studyHall.jpg");
        }
        return resImg;
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
    }

    /**
     * adds a room to the database.
     * @param mouseEvent the clicking of the mouse.
     * @author Scott.
     */
    @FXML
    public void addButtonHandler(MouseEvent mouseEvent) {
        try {
            int capacity = Integer.parseInt(capacityTextField.getText());
            String roomName = roomNameTextField.getText();
            String buildingName = buildingComboBox.getValue();
            String typeName = typeNameComboBox.getValue();
            int typeint = 5;
            List<Type> types = ServerCommunication.getTypes();
            for (Type t : types) {
                if (t.getName().equals(typeName)) {
                    typeint = t.getType_id();
                    break;
                }
            }
            ServerCommunication.createRoom(capacity, roomName, buildingName, typeint);
            confirmationText.setText("Room added successfully.");

        } catch (Exception e) {
            confirmationText.setText("Room failed to add.");
            throw new IllegalArgumentException("invalid input");
        }
    }
}
