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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.UserReservationInfo;


public class SeeAllBikeReservationsSceneController implements Initializable {

    @FXML
    private VBox userReservationInfoList;

    private static int reservationID;

    private static int status;

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        SeeAllBikeReservationsSceneController.status = status;
    }

    public static int getReservationID() {
        return reservationID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SeeAllBikeReservationsSceneController.status = 0;
        try {
            ArrayList<UserReservationInfo> reservations = ServerCommunication.getUserReservationInfo(MainApp.user.getUserId());
            userReservationInfoList.getChildren().clear();
            displayBikeReservationInfo();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        reservationID = -1;

    }

    public void backToLogin(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }


    /**
     * This method adds the list of all bike reservations to the Vbox.
     * @author - Sartori Kendra
     * @throws IOException - exception thrown if the file is not found
     * @throws URISyntaxException - exception thrown when url is not correct
     */
    public void displayBikeReservationInfo() throws  IOException, URISyntaxException {
        List<String> reservations = ServerCommunication.bikeReservationList();
        for (String s : reservations) {
            Text t = new Text(s);
            t.setStyle("-fx-border-width:3;");
            t.setStyle("-fx-border-color:blue;");
            t.setStyle("-fx-border-style: solid;");
            t.setStyle("-fx-background-color: #99ebff;");
            t.setStyle("-fx-color: purple;");
            HBox reservationinfo = new HBox(t);
            Button deleteButton = new Button("Remove");
            deleteButton.setStyle("-fx-base: purple");
            deleteButton.setAlignment(Pos.BOTTOM_CENTER);

            Text space = new Text("  ");

            reservationinfo.setPadding(new Insets(10, 0, 10, 0));
            HBox container =  new HBox(reservationinfo, space, deleteButton);
            container.setStyle("-fx-border-width:3;");
            container.setStyle("-fx-border-color:blue;");
            container.setStyle("-fx-border-style: solid;");
            container.setAlignment(Pos.TOP_LEFT);
            userReservationInfoList.getChildren().add(container);

            String[] res = s.split(",");
            String resId = res[0];
            String[] res2 = resId.split(": ");
            String resIdString = res2[1];
            int id = Integer.parseInt(resIdString);

            deleteButton.setOnAction(event -> {
                try {
                    deletePopUp(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    /**
     * This method starts the pop up.
     * @author Sartori Kendra
     * @param id - id of the reservation to be deleted
     * @throws IOException - throws exception if the file is not found
     */
    @FXML
    public void deletePopUp(int id) throws IOException {
        SeeAllBikeReservationsSceneController.reservationID = id;
        SeeAllBikeReservationsSceneController.setStatus(1);
        Parent root = FXMLLoader.load(getClass().getResource("/deleteReservationPopUp.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }


}
