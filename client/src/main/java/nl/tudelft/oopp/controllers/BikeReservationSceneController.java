package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import nl.tudelft.oopp.communication.Building;

import nl.tudelft.oopp.communication.ServerCommunication;

public class BikeReservationSceneController implements Initializable {

    @FXML
    private VBox buildingList;
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
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }

    /**
     * On click this method checks whether the user already has a bike reservation on that day.
     * If he does, a warning will show up, else he can continue doing a new reservation.
     * @author Sartori Kendra
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
            getBuildings();
        }
    }
}
