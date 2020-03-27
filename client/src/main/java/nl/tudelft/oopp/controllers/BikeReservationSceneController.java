package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.AvailableRoom;
import nl.tudelft.oopp.communication.Building;

import nl.tudelft.oopp.communication.ServerCommunication;

public class BikeReservationSceneController implements Initializable {

    @FXML
    private VBox buildingList;
    @FXML
    private Text username;
    @FXML
    private DatePicker datePickerBike;

    private static String buildingName;
    private static String day;
    private static boolean hasReserved;

    public static String getBuildingName() {
        return buildingName;
    }

    public static void setBuildingName(String buildingName) {
        BikeReservationSceneController.buildingName = buildingName;
    }

    public static String getDay() {
        return day;
    }

    public static void setDay(String day) {
        BikeReservationSceneController.day = day;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        hasReserved = false;
    }

    public void backToLogin(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");

    }

    /**
     * You won't see the buildings where there are no bikes left to reserve.
     * @author Alexandru Bobe
     */
    public void getBuildings() throws IOException, URISyntaxException {
        List<Building> listOfBuildings = ServerCommunication.getBuildings();
        buildingList.getChildren().clear();
        for (Building building : listOfBuildings) {
            if (ServerCommunication.getNumberOfAvailableBikes(building.getBuilding_Name(), datePickerBike.getValue()) > 0) {
                Text buildingName = new Text(building.getBuilding_Name());
                Button reserveButton = new Button("Reserve");
                reserveButton.setAlignment(Pos.TOP_RIGHT);
                reserveButton.setOnAction(event -> {
                    try {
                        reservePopUp(building.getBuilding_Name(), datePickerBike.getValue().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                HBox container = new HBox(buildingName, reserveButton);
                buildingList.getChildren().add(container);
            }

        }
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

    /**
     * This starts the pop-up.
     * @param buildingName building name
     * @param day day
     * @throws IOException exception
     */
    public void reservePopUp(String buildingName, String day) throws IOException {
        BikeReservationSceneController.buildingName = buildingName;
        BikeReservationSceneController.day = day;

        Parent root = FXMLLoader.load(getClass().getResource("/bikeReservationPopUpScene.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 318.4, 147.2);
        st.setScene(sc);
        st.show();
    }

    /**
     * On click this method checks whether the user already has a bike reservation on that day.
     * If he does, a warning will show up, else he can continue doing a new reservation.
     * @author Sartori Kendra
     * @author Eli Shamayev
     * @param actionEvent - the event that happens
     * @throws IOException - exception
     * @throws URISyntaxException - exception thrown if the syntax is wrong
     */
    public void searchButtonHandler(ActionEvent actionEvent) throws IOException, URISyntaxException {
        System.out.println(ServerCommunication.hasBikeReservation((Date.valueOf(datePickerBike.getValue()))));
        if (ServerCommunication.hasBikeReservation(Date.valueOf(datePickerBike.getValue()))) {
            hasReserved = true;
        }
        if (hasReserved) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("You have already reserved bike on the chosen date.\n "
                    + "You are only allowed to reserve one bike per day.\n"
                    + "Please choose another date!\n");
            warning.show();
            //set hasReserved to false now
            hasReserved = false;
        } else {
            ArrayList<Building> availableBuildings = ServerCommunication.getBuildings();
            for (Building ar : availableBuildings) {
                createAvailableBuildingView(ar);nm
            }
        }
    }

    /**
     * this will create hboxs to display the available buildings in which you can reserve a bike from.
     * @Author Eli Shamayev
     * @param ar
     */
    public void createAvailableBuildingView(Building ar) {

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

        Image image = getCorrectImage(ar.getBuilding_Name());
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
        HBox buildingNameBox = new HBox();
        buildingNameBox.setAlignment(Pos.CENTER_LEFT);
        buildingNameBox.setPrefHeight(25);
        Text buildingName = new Text(ar.getBuilding_Name());
        buildingName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        buildingName.setFill(Color.BLACK);
        buildingNameBox.getChildren().add(buildingName);

        //this second one adds the type of the room + capacity
        HBox buildingInfo1 = new HBox();
        buildingInfo1.setAlignment(Pos.TOP_LEFT);
        buildingInfo1.setPrefHeight(27);
        Text buildingType = new Text(ar.getBuilding_Name());
        buildingType.setFont(Font.font("Arial", 16));

        buildingInfo1.getChildren().addAll(buildingType);

        infoBox.getChildren().addAll(buildingNameBox, buildingInfo1);

        //add info VBox and the image HBox to the main HBox and set their margins
        mainBox.getChildren().add(imgBox);
        mainBox.setMargin(imgBox, new Insets(0, 0, 0, 0));
        mainBox.getChildren().add(infoBox);
        mainBox.setMargin(infoBox, new Insets(5, 0, 5, 0));

        //add mainBox to the outermost VBox that contains all these room reservation Hboxes
        buildingList.getChildren().add(mainBox);
        buildingList.setMargin(mainBox, new Insets(5, 18, 5, 0));
    }

    /**
     * this will identify the approriate building picture for the building name given
     * @Author Eli Shamayev
     * @param buildingName
     * @return image of building
     */
    public Image getCorrectImage(String buildingName) {
        Image resImg = null;
        if (getBuildingName().equals("AS")) {
            resImg = new Image("images/aerospace_building.jpg");
        }
        if (getBuildingName().equals("Pulse")) {
            resImg = new Image("images/pulse_building.jpg");
        }
        if (getBuildingName().equals("NS")) {
            resImg = new Image("images/natuurkunde_building.jpg");
        }
        if (getBuildingName().equals("DW")) {
            resImg = new Image("images/dw_building.jpg");
        }
        if (getBuildingName().equals("ME")) {
            resImg = new Image("images/mechanicalengineering_building.jpg");
        }
        if (getBuildingName().equals("CSE")) {
            resImg = new Image("images/cs_building.jpg");
        }
        return resImg;
    }
}
