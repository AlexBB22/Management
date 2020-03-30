package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.UserReservationInfo;



public class SeeAllRoomReservationsSceneController implements Initializable {


    @FXML
    private VBox userReservationInfoList;

    private static int reservationID;

    private static int status;

    public static int getReservationID() {
        return reservationID;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        SeeAllRoomReservationsSceneController.status = status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SeeAllRoomReservationsSceneController.status = 0;
        try {
            ArrayList<UserReservationInfo> reservations = ServerCommunication.getUserReservationInfo(MainApp.user.getUserId());
            userReservationInfoList.getChildren().clear();
            for (UserReservationInfo uri: reservations) {
                System.out.println("here");
                displayUserReservationInfo(uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        reservationID = -1;

    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

    /**
     * This method creates a list of all the room reservations and adds them to the Vbox.
     * @author Kanish Dwivedi
     * @param uri - the UserReservationInfo object which is to be displayed.
     */
    public void displayUserReservationInfo(UserReservationInfo uri) {

        Text information = new Text("\nBuilding: " + uri.getBuildingName()
                + "\nRoom: " + uri.getRoomName() + "\nType: " + uri.getName()  + "\nDay: " + uri.getDay() + "\nStartTime: " + uri.getStartTime() + "\nEndTime: " + uri.getEndTime() + "\n\n");
        information.setTextAlignment(TextAlignment.CENTER);
        information.setFont(Font.font("Chalkboard SE", 16));
        HBox reservationinfo = new HBox(information);

        reservationinfo.setStyle("-fx-border-width:3;");
        reservationinfo.setStyle("-fx-border-color:blue;");
        reservationinfo.setStyle("-fx-border-style: solid;");
        reservationinfo.setStyle("-fx-background-color: #99ebff;");


        Button deleteButton = new Button("Remove");
        deleteButton.setStyle("-fx-base: blue");

        deleteButton.setAlignment(Pos.CENTER_LEFT);
        reservationinfo.setPadding(new Insets(10, 0, 10, 0));
        HBox container =  new HBox(reservationinfo, deleteButton);
        container.setStyle("-fx-border-width:3;");
        container.setStyle("-fx-border-color:blue;");
        container.setStyle("-fx-border-style: solid;");
        container.setAlignment(Pos.CENTER);

        userReservationInfoList.getChildren().add(container);

        int id = uri.getReservationID();
        System.out.println(id);
        deleteButton.setOnAction(event -> {
            try {
                System.out.println(id);
                deletePopUp(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method starts the pop up.
     * @author Sartori Kendra
     * @param id - id of the reservation to be deleted
     * @throws IOException - throws exception if the file is not found
     */
    @FXML
    public void deletePopUp(int id) throws IOException {
        SeeAllRoomReservationsSceneController.reservationID = id;
        SeeAllRoomReservationsSceneController.setStatus(1);
        Parent root = FXMLLoader.load(getClass().getResource("/deleteReservationPopUp.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }


}
