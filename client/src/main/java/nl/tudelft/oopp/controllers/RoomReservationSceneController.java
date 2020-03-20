package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import com.sun.tools.javac.Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.AvailableRoom;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.OverridableRoom;
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
    @FXML private static Button searchButton;
    @FXML private VBox sideMenu;
    @FXML private HBox topBar;

    //This arrayList just saves all the buildings from the query made during initialisation
    private ArrayList<Building> buildingList;

    //These attributes are used to save information about the last reservation made
    //the getters of these methods are called in ReservationPopUpSceneController.java
    private static int reservationId;
    private static int roomID;
    private static String roomName;
    private static String buildingName;
    private static String startTime;
    private static String endTime;
    private static String day;
    //reservedBy is the username of the person who has reserved the overridable room
    private static String reservedBy;
    private static String reservedByRole;

    private boolean hasReserved;

    public static int getReservationId() {
        return reservationId;
    }

    public static int getRoomID() {
        return roomID;
    }

    public static String getBuildingName() {
        return buildingName;
    }

    public static String getStartTime() {
        return startTime;
    }

    public static String getEndTime() {
        return endTime;
    }

    public static String getRoomName() {
        return roomName;
    }

    public static String getDay() {
        return day;
    }

    public static String getReservedBy() {
        return reservedBy;
    }

    public static String getReservedByRole() {
        return reservedByRole;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topBar.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.rgb(65, 165, 212), Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(5), Insets.EMPTY)));

        hasReserved = false;

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
                System.out.println(b.getBuilding_Name() + " " + startTime);
                endTime = b.getClosing();
            }
        }

        ObservableList<String> times = FXCollections.observableArrayList();
        //add specific start time if its not the regular one
        if (!startTime.toString().equals("08:45:00")) {
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
        if (!endTime.toString().equals("17:45:00")) {
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
     * users role when the "search" button is clicked.
     * @author Kanish Dwivedi, Hidde, Niels
     * @param actionEvent the click event that happens
     * @throws URISyntaxException throws exception when wrong syntax is given
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent)
            throws URISyntaxException, IOException {
        //Depending on the role of the user, we call its respective function handler
        String role = MainApp.user.getRole().getRoleName();

        if (role.equals("Student") || role.equals("Staff")) {
            String[] timeSlot = timeSlotComboBox.getValue().split("-");
            if (ServerCommunication.hasReservation(Date.valueOf(datePicker.getValue()),
                    Time.valueOf(timeSlot[0]), Time.valueOf(timeSlot[1]))) {
                hasReserved = true;
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("Conflict");
                warning.setContentText("You have already reserved a room for the given timeslot and date. "
                        + "You are not authorized to reserve another room for the given criteria.");
                warning.show();
                //set hasReserved to false now
                hasReserved = false;
                return;
            }
            if (role.equals("Student")) {
                getRoomsForStudent();
            }
            if (role.equals("Staff")) {
                getRoomsForStaff();
            }
        }
        if (MainApp.user.getRole().getRoleName().equals("Teacher")) {
            getRoomsForTeacher();
        }

        //Set up the static variables attribute
        //I set them up here, and not later as because if set later, then its possible for the user to change the
        //time slot in the UI, and not click search. Thus, the incorrect timeslot value will be saved.
        String[] timeslot = timeSlotComboBox.getValue().split("-");
        RoomReservationSceneController.startTime = timeslot[0];
        RoomReservationSceneController.endTime = timeslot[1];
        RoomReservationSceneController.day = datePicker.getValue().toString();
        RoomReservationSceneController.buildingName = buildingComboBox.getValue();
    }

    /**
     * Get all the available rooms for students.
     * @author Hidde Agterberg
     * @throws IOException - Exception thrown if I/O fails
     * @throws URISyntaxException - Exception thrown if the URl which is used to communicate with DB is invalid
     */
    public void getRoomsForStudent() throws IOException, URISyntaxException {
        // Clear vbox before adding all the room items into it
        roomList.getChildren().clear();

        //Making the title "Available rooms" and adding it to the main Vbox
        Text availableRoomTitle = new Text("Available Rooms");
        availableRoomTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        availableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(availableRoomTitle);

        //Getting the start and end time the user selected to make query to DB
        String[] timeSlot = timeSlotComboBox.getValue().split("-");
        String starttime = timeSlot[0];
        String endtime = timeSlot[1];

        //Get List of available rooms
        ArrayList<AvailableRoom> availableRooms = ServerCommunication.getOnlyAvailableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime);

        if (availableRooms.size() == 0) {
            Text info = new Text("No available rooms.");
            info.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            roomList.getChildren().add(info);
            roomList.setMargin(info, new Insets(5, 0, 10, 15));
        } else {
            //Calling method createAvailableView with each room so that its shown to the user and added into the vbox
            for (AvailableRoom ar: availableRooms) {
                createAvailableRoomView(ar);
            }
        }

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
        availableRoomTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        availableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(availableRoomTitle);


        //Getting the start and end time the user selected to make query to DB
        String[] timeSlot = timeSlotComboBox.getValue().split("-");
        String starttime = timeSlot[0];
        String endtime = timeSlot[1];

        //Get List of available rooms
        ArrayList<AvailableRoom> availableRooms = ServerCommunication.getOnlyAvailableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime);

        if (availableRooms.size() == 0) {
            Text info = new Text("No available rooms.");
            info.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            roomList.getChildren().add(info);
            roomList.setMargin(info, new Insets(5, 0, 10, 15));
        } else {
            //Calling method createAvailableView with each room so that its shown to the user and added into the vbox
            for (AvailableRoom ar: availableRooms) {
                createAvailableRoomView(ar);
            }
        }

        //Making the title "Overridable rooms" and adding it after all the Available rooms have been added to the VBox
        Text overridableRoomTitle = new Text("Overridable Rooms");
        overridableRoomTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        overridableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(overridableRoomTitle);

        //Get List of overridable rooms
        //Call with roleId = 1, as Staff wants overridable rooms of Students (which are role 1)
        ArrayList<OverridableRoom> overridableRooms = ServerCommunication.getOnlyOverridableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime, 1);

        if (overridableRooms.size() == 0) {
            Text info = new Text("No overridable rooms.");
            info.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            roomList.getChildren().add(info);
            roomList.setMargin(info, new Insets(5, 0, 10, 15));
        } else {
            //Calling method creatOverridableView with each room so that its shown to the user and added into the vbox
            for (OverridableRoom or: overridableRooms) {
                createOverridableRoomView(or);
            }
        }
    }

    /**
     * This method initiates the communication with the database to first get a list of all available rooms
     * and then also get a list of all overridable rooms (ie. rooms reserved by students and staff)
     *
     * @author - Niels Tomassen
     * @throws IOException - Exception thrown if I/O fails
     * @throws URISyntaxException - Exception thrown if the URl which is used to communicate with DB is invalid
     */
    public void getRoomsForTeacher() throws IOException, URISyntaxException {
        // Clear vbox before adding all the room items into it
        roomList.getChildren().clear();

        //Making the title "Available rooms" and adding it to the main Vbox
        Text availableRoomTitle = new Text("Available Rooms");
        availableRoomTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        availableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(availableRoomTitle);

        //Getting the start and end time the user selected to make query to DB
        String[] timeSlot = timeSlotComboBox.getValue().split("-");
        String starttime = timeSlot[0];
        String endtime = timeSlot[1];

        //Get List of available rooms
        ArrayList<AvailableRoom> availableRooms = ServerCommunication.getOnlyAvailableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime);

        if (availableRooms.size() == 0) {
            Text info = new Text("No available rooms.");
            info.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            roomList.getChildren().add(info);
            roomList.setMargin(info, new Insets(5, 0, 10, 15));
        } else {
            //Calling method createAvailableView with each room so that its shown to the user and added into the vbox
            for (AvailableRoom ar: availableRooms) {
                createAvailableRoomView(ar);
            }
        }

        //Making the title "Overridable rooms" and adding it after all the Available rooms have been added to the VBox
        Text overridableRoomTitle = new Text("Overridable Rooms");
        overridableRoomTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        overridableRoomTitle.setFill(Color.BLUE);
        roomList.getChildren().add(overridableRoomTitle);

        //Get List of overridable rooms
        //Call with roleId = 1 and roleId = 2, as Staff wants overridable rooms of Students (which are role 1) and staff (which are role 2)
        ArrayList<OverridableRoom> overridableRooms = ServerCommunication.getOnlyOverridableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime, 1);
        ArrayList<OverridableRoom> overridableRooms2 = ServerCommunication.getOnlyOverridableRooms(buildingComboBox.getValue(), datePicker.getValue(),
                starttime, endtime, 2);
        overridableRooms.addAll(overridableRooms2);

        if (overridableRooms.size() == 0) {
            Text info = new Text("No overridable rooms.");
            info.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            roomList.getChildren().add(info);
            roomList.setMargin(info, new Insets(5, 0, 10, 15));
        } else {
            //Calling method creatOverridableView with each room so that its shown to the user and added into the vbox
            for (OverridableRoom or: overridableRooms) {
                createOverridableRoomView(or);
            }
        }

    }


    /**
     * This method simply creates the HBox entity that will show all important information and contain functionality
     * for the user to see the room and reserve it.
     * @author Kanish Dwivedi
     * @param or - the Overrideable room which is to be shown and to be added to the main VBox
     */
    public void createOverridableRoomView(OverridableRoom or) throws FileNotFoundException {
        //Making outermost box
        HBox mainBox = new HBox();
        mainBox.setPrefHeight(160);
        mainBox.setPrefWidth(560);
        mainBox.setStyle("-fx-border-width: 2; -fx-border-color:  #2ad8ff;");

        //image Hbox
        HBox imgBox = new HBox();
        imgBox.setPrefWidth(200);
        imgBox.setPrefHeight(150);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setNodeOrientation(NodeOrientation.INHERIT);

        Image image = getCorrectImage(or.getName());
        ImageView imV = new ImageView();
        imV.setImage(image);
        imV.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        imV.setFitWidth(180);
        imV.setFitHeight(135);
        imgBox.getChildren().add(imV);
        imgBox.setMargin(imV, new Insets(5, 5, 5, 5));


        //information VBox
        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setNodeOrientation(NodeOrientation.INHERIT);
        infoBox.setPrefHeight(145);
        infoBox.setPrefWidth(360);

        //the info VBox has 5 Hboxes inside that have the name of room and its capacity and all its attributes
        //this first one adds the name of room
        HBox roomNameBox = new HBox();
        roomNameBox.setAlignment(Pos.CENTER_LEFT);
        roomNameBox.setPrefHeight(25);
        Text roomName = new Text(or.getRoomName());
        roomName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        roomName.setFill(Color.BLACK);
        roomNameBox.getChildren().add(roomName);

        //this second one adds the type of the room + capacity
        HBox roomInfo1 = new HBox();
        roomInfo1.setAlignment(Pos.TOP_LEFT);
        roomInfo1.setPrefHeight(27);
        Text roomType = new Text(or.getName());
        roomType.setFont(Font.font("Arial", 16));
        Text roomCapacity = new Text("\t Capacity: " + or.getCapacity());
        roomCapacity.setFont(Font.font("Arial", 16));

        roomInfo1.getChildren().addAll(roomType, roomCapacity);

        //this third one adds three attributes of the room
        HBox roomInfo2 = new HBox();
        roomInfo2.setAlignment(Pos.TOP_LEFT);
        roomInfo2.setPrefHeight(27);
        Text isTV = new Text("\t TV: " + yesNO(or.isTv()));
        isTV.setFont(Font.font("Arial", 15));
        Text isClicker = new Text("    Clicker: " + yesNO(or.isClicker()));
        isClicker.setFont(Font.font("Arial", 15));
        Text isWhiteboard = new Text("    Whiteboard: " + yesNO(or.isWhiteboard()));
        isWhiteboard.setFont(Font.font("Arial", 15));

        roomInfo2.getChildren().addAll(isTV, isClicker, isWhiteboard);

        //this fourth one adds power outles attribute of the room
        HBox roomInfo3 = new HBox();
        roomInfo3.setAlignment(Pos.TOP_LEFT);
        roomInfo3.setPrefHeight(27);
        Text isPowerOutlet = new Text("\t Power Outlets: " + yesNO(or.isPowerOutlets()));
        isPowerOutlet.setFont(Font.font("Arial", 15));

        roomInfo3.getChildren().add(isPowerOutlet);

        //this fifth one adds reserved by information
        HBox roomInfo4 = new HBox();
        roomInfo4.setAlignment(Pos.BOTTOM_LEFT);
        roomInfo4.setPrefHeight(30);

        Button overrideButton = new Button("Reserved By:  " + or.getUserName() + " (" + or.getRoleName() + ")    OVERRIDE");

        overrideButton.setPrefSize(345, 30);
        overrideButton.setMaxSize(345, 30);
        overrideButton.setStyle("-fx-background-color: #2f93ff; -fx-text-fill: white; -fx-text-alignment: center; "
                + "-fx-font-family: 'Arial'; -fx-font-size: 13px; -fx-font-weight: bold;");
        overrideButton.setOnAction(event -> {
            try {
                overridePopUp(or.getRoomName(), or.getUserName(), or.getRoleName(), or.getReservationID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        overrideButton.setCursor(Cursor.HAND);
        roomInfo4.getChildren().add(overrideButton);
        roomInfo4.setMargin(overrideButton, new Insets(0, 5, 0, 5));

        infoBox.getChildren().addAll(roomNameBox, roomInfo1, roomInfo2, roomInfo3, roomInfo4);

        //add info VBox and the image HBox to the main HBox and set their margins
        mainBox.getChildren().add(imgBox);
        mainBox.setMargin(imgBox, new Insets(0, 0, 0, 0));
        mainBox.getChildren().add(infoBox);
        mainBox.setMargin(infoBox, new Insets(5, 0, 5, 0));

        //add mainBox to the outermost VBox that contains all these room reservation Hboxes
        roomList.getChildren().add(mainBox);
        roomList.setMargin(mainBox, new Insets(5, 18, 5, 0));
    }

    /**
     * This method simply creates the HBox entity that will show all important information and contain functionality
     * for the user to see the room and reserve it.
     * @author Kanish Dwivedi
     * @param ar - the Available room which is to be shown and added to the main VBox
     */
    public void createAvailableRoomView(AvailableRoom ar) {
        //Making outermost box
        HBox mainBox = new HBox();
        mainBox.setPrefHeight(160);
        mainBox.setPrefWidth(560);
        mainBox.setStyle("-fx-border-width: 2; -fx-border-color:  #2ad8ff;");

        //image Hbox
        HBox imgBox = new HBox();
        imgBox.setPrefWidth(200);
        imgBox.setPrefHeight(150);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setNodeOrientation(NodeOrientation.INHERIT);

        Image image = getCorrectImage(ar.getName());
        ImageView imV = new ImageView();
        imV.setImage(image);
        imV.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        imV.setFitWidth(180);
        imV.setFitHeight(135);
        imgBox.getChildren().add(imV);
        imgBox.setMargin(imV, new Insets(5, 5, 5, 5));

        //information VBox
        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setNodeOrientation(NodeOrientation.INHERIT);
        infoBox.setPrefHeight(145);
        infoBox.setPrefWidth(360);

        //the info VBox has 5 Hboxes inside that have the name of room and its capacity and all its attributes
        //this first one adds the name of room
        HBox roomNameBox = new HBox();
        roomNameBox.setAlignment(Pos.CENTER_LEFT);
        roomNameBox.setPrefHeight(25);
        Text roomName = new Text(ar.getRoomName());
        roomName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        roomName.setFill(Color.BLACK);
        roomNameBox.getChildren().add(roomName);

        //this second one adds the type of the room + capacity
        HBox roomInfo1 = new HBox();
        roomInfo1.setAlignment(Pos.TOP_LEFT);
        roomInfo1.setPrefHeight(27);
        Text roomType = new Text(ar.getName());
        roomType.setFont(Font.font("Arial", 16));
        Text roomCapacity = new Text("\t Capacity: " + ar.getCapacity());
        roomCapacity.setFont(Font.font("Arial", 16));

        roomInfo1.getChildren().addAll(roomType, roomCapacity);

        //this third one adds three attributes of the room
        HBox roomInfo2 = new HBox();
        roomInfo2.setAlignment(Pos.TOP_LEFT);
        roomInfo2.setPrefHeight(27);
        Text isTV = new Text("\t TV: " + yesNO(ar.isTv()));
        isTV.setFont(Font.font("Arial", 15));
        Text isClicker = new Text("    Clicker: " + yesNO(ar.isClicker()));
        isClicker.setFont(Font.font("Arial", 15));
        Text isWhiteboard = new Text("    Whiteboard: " + yesNO(ar.isWhiteboard()));
        isWhiteboard.setFont(Font.font("Arial", 15));

        roomInfo2.getChildren().addAll(isTV, isClicker, isWhiteboard);

        //this fourth one adds power outles attribute of the room
        HBox roomInfo3 = new HBox();
        roomInfo3.setAlignment(Pos.TOP_LEFT);
        roomInfo3.setPrefHeight(27);
        Text isPowerOutlet = new Text("\t Power Outlets: " + yesNO(ar.isPowerOutlets()));
        isPowerOutlet.setFont(Font.font("Arial", 15));

        roomInfo3.getChildren().add(isPowerOutlet);

        //this fifth one adds reserved by information
        HBox roomInfo4 = new HBox();
        roomInfo4.setAlignment(Pos.BOTTOM_LEFT);
        roomInfo4.setPrefHeight(30);

        Button reserveButton = new Button("Reserve");
        reserveButton.setPrefSize(345, 30);
        reserveButton.setMaxSize(345, 30);
        reserveButton.setStyle("-fx-background-color: #2f93ff; -fx-text-fill: white; -fx-text-alignment: center; "
                + "-fx-font-family: 'Arial'; -fx-font-size: 13px; -fx-font-weight: bold;");
        reserveButton.setOnAction(event -> {
            try {
                reservePopUp(ar.getRoomName(), ar.getRoomID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        reserveButton.setCursor(Cursor.HAND);
        roomInfo4.getChildren().add(reserveButton);
        roomInfo4.setMargin(reserveButton, new Insets(0, 5, 0, 5));

        infoBox.getChildren().addAll(roomNameBox, roomInfo1, roomInfo2, roomInfo3, roomInfo4);

        //add info VBox and the image HBox to the main HBox and set their margins
        mainBox.getChildren().add(imgBox);
        mainBox.setMargin(imgBox, new Insets(0, 0, 0, 0));
        mainBox.getChildren().add(infoBox);
        mainBox.setMargin(infoBox, new Insets(5, 0, 5, 0));

        //add mainBox to the outermost VBox that contains all these room reservation Hboxes
        roomList.getChildren().add(mainBox);
        roomList.setMargin(mainBox, new Insets(5, 18, 5, 0));

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

    /**
     * This method is simply used during creating the room view boxes for the attribute. It returns a
     * string "Yes" or "No" corresponding to the boolean.
     * @author - Kanish Dwivedi
     * @param trueFalse - boolean value representing if a room has a certain attribute or not
     * @return - Yes or No based on the boolean
     */
    public String yesNO(boolean trueFalse) {
        if (trueFalse) {
            return "Yes";
        }
        return "No";
    }


    /**
     * A method to create a popup for a new room reservation.
     * @author Scott
     * @throws IOException - exception thrown when file not found
     */
    public void reservePopUp(String roomName, int roomID) throws IOException {
        if (hasReserved) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("You have already reserved a room for the given timeslot and date. "
                    + "You are not authorized to reserve another room for the given criteria.");
            warning.show();
            return;
        }
        //Setting static variables to properties given so that these can be accessed in the other controller class
        RoomReservationSceneController.roomName = roomName;
        RoomReservationSceneController.roomID = roomID;

        Parent root = FXMLLoader.load(getClass().getResource("/reservationPopUpScene.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }

    /**
     * A method to create a popup for a new override.
     * @author Kanish Dwivedi
     * @throws IOException - exception thrown when file is not found
     */
    public void overridePopUp(String roomName, String reservedBy, String reservedByRole, int reservationId) throws IOException {
        RoomReservationSceneController.roomName = roomName;
        RoomReservationSceneController.reservedBy = reservedBy;
        RoomReservationSceneController.reservedByRole = reservedByRole;
        RoomReservationSceneController.reservationId = reservationId;

        Parent root = FXMLLoader.load(getClass().getResource("/overrideReservationPopUpScene.fxml"));
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

    public static Stage getStage() {
        return (Stage) searchButton.getScene().getWindow();
    }
}
