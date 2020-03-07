package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.AvailableRoom;
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
    @FXML private ScrollPane scrollPane;

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
     * Search button handler. This method calls three other methods to get the appropriate rooms depending on the
     * users role.
     * @author Kanish Dwivedi, Hidde, Niels
     * @param actionEvent the click event that happens
     * @throws URISyntaxException throws exception when wrong syntax is given
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent)
            throws URISyntaxException, IOException {
        //Depending on the role of the user, we call its respective function handler

        if (MainApp.user.getRole().getRoleName().equals("Student")) {
            getRoomsForStudent();
        }
        if (MainApp.user.getRole().getRoleName().equals("Staff")) {
            getRoomsForStaff();
        }
        if (MainApp.user.getRole().getRoleName().equals("Teacher")) {
            getRoomsForTeacher();
        }
    }

    public void getRoomsForStudent() {
        //add code here Hidde
    }

    /**
     * This method initiates the communication with the database to first get a list of all available rooms
     * and then also get a list of all overridable rooms (ie. rooms reserved by students)
     * @author - Kanish Dwivedi
     * @throws IOException - Exception thrown if I/O fails
     * @throws URISyntaxException - Exception thrown if the URl which is used to communicate with DB is invalid
     */
    public void getRoomsForStaff() throws IOException, URISyntaxException {
        // Clear vbox before adding all the room items into it
        roomList.getChildren().clear();

        //Making the title "Available rooms" and adding it to the main Vbox
        Text availableRoomTitle = new Text("Available Rooms");
        availableRoomTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        availableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(availableRoomTitle);

        //Getting the start and end time the user selected to make query to DB
        String[] timeSlot = timeSlotComboBox.getValue().split("-");
        String starttime = timeSlot[0];
        String endtime = timeSlot[1];

        //Get List of available rooms
        ArrayList<AvailableRoom> availableRooms = ServerCommunication.getOnlyAvailableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime);

        //Calling method createAvailableView with each room so that its shown to the user and added into the vbox
        for (AvailableRoom ar: availableRooms) {
            createAvailableRoomView(ar);
        }

        //Making the title "Overridable rooms" and adding it after all the Available rooms have been added to the VBox
        Text overridableRoomTitle = new Text("Overridable Rooms");
        overridableRoomTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        overridableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(overridableRoomTitle);

        //Get List of overridable rooms
        //Call with roleId = 1, as Staff wants overridable rooms of Students (which are role 1)
        ArrayList<OverridableRoom> overridableRooms = ServerCommunication.getOnlyOverridableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime, 1);

        //Calling method creatOverridableView with each room so that its shown to the user and added into the vbox
        for (OverridableRoom or: overridableRooms) {
            createOverridableRoomView(or);
        }
    }

    public void getRoomsForTeacher() {
        //add code here Niels
    }


    /**
     * This method simply creates the HBox entity that will show all important information and contain functionality
     * for the user to see the room and reserve it.
     * @author Kanish Dwivedi
     * @param or - the Overrideable room which is to be shown and to be added to the main VBox
     */
    public void createOverridableRoomView(OverridableRoom or) throws FileNotFoundException {
        //Setting up image and override button
        Image image = getCorrectImage(or.getName());
        ImageView imV = new ImageView();
        imV.setImage(image);
        imV.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        imV.setFitWidth(100);
        imV.setFitHeight(85);

        Button reserveBtn = new Button("Override");
        reserveBtn.setAlignment(Pos.CENTER_LEFT);
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

        //Make the VBox with the image and the button
        VBox imageAndBtn = new VBox(imV, reserveBtn);

        //Make gridpane and add information to it
        GridPane gridPane = new GridPane();
        gridPane.add(new Text(or.getRoomName()), 0, 0);
        gridPane.add(new Text(or.getName()), 1, 0);
        gridPane.add(new Text("Capacity: " + Integer.toString(or.getCapacity())), 2, 0);

        gridPane.add(new Text("Clicker: " + Boolean.toString(or.isClicker())), 0, 1);
        gridPane.add(new Text("PowerOutlets: " + Boolean.toString(or.isPowerOutlets())), 1, 1);
        gridPane.add(new Text("TV: " + Boolean.toString(or.isTv())), 2, 1);

        gridPane.add(new Text("Whiteboard: " + Boolean.toString(or.isWhiteboard())), 0, 2);
        gridPane.add(new Text("Reserved by: " + or.getUserName()), 1, 2);
        gridPane.add(new Text("(" + or.getRoleName() + ")"), 2, 2);

        //Finally add the Gridpane (with information about room) and VBox (with image and button) into a HBox
        //Then add the Hbox into the original Vbox container
        HBox orList = new HBox(imageAndBtn, gridPane);
        orList.setPadding(new Insets(10,0,10,0));
        roomList.getChildren().add(orList);
    }

    /**
     * This method simply creates the HBox entity that will show all important information and contain functionality
     * for the user to see the room and reserve it.
     * @autho Kanish Dwivedi
     * @param ar - the Available room which is to be shown and added to the main VBox
     */
    public void createAvailableRoomView(AvailableRoom ar) {
        //setting up image and reserve button
        //Setting up image and override button
        Image image = getCorrectImage(ar.getName());
        ImageView imV = new ImageView();
        imV.setImage(image);
        imV.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        imV.setFitWidth(100);
        imV.setFitHeight(85);

        Button reserveBtn = new Button("Reserve");
        reserveBtn.setAlignment(Pos.CENTER_LEFT);
        reserveBtn.setOnAction(event -> {
            try {
                reservePopUp(ar.getBuildingName(),
                        ar.getRoomName(),
                        "date", // TODO: datePicker.getValue().toString(),
                        timeSlotComboBox.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Make the VBox with the image and the button
        VBox imageAndBtn = new VBox(imV, reserveBtn);

        //Make the Gridpane and add room information into it
        GridPane gridPane = new GridPane();
        gridPane.add(new Text(ar.getRoomName()), 0, 0);
        gridPane.add(new Text(ar.getName() + "   " + "Capacity: " + ar.getCapacity()), 1, 0);

        gridPane.add(new Text("TV: " + ar.isTv()), 0, 1);
        gridPane.add(new Text("PowerOutlets: " + ar.isPowerOutlets()), 1, 1);

        gridPane.add(new Text("Clicker: " + ar.isClicker()), 0, 2);
        gridPane.add(new Text("Whiteboard: " + ar.isWhiteboard()), 1, 2);

        //Finally add the Gridpane (with information about room) and VBox (with image and button) into a HBox
        //Then add the Hbox into the original Vbox container
        HBox arList = new HBox(imageAndBtn, gridPane);
        arList.setPadding(new Insets(10, 0, 10, 0));

        //Add the HBox that contains the GridPane and VBox into the main VBox container
        roomList.getChildren().add(arList);
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

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/accountScene.fxml", "Account settings");
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

}
