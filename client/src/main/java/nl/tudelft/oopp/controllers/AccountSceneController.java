package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.UserReservationInfo;


public class AccountSceneController implements Initializable {

    @FXML
    private Text accountId;

    @FXML
    private Text accountRole;

    @FXML
    private Text accountEmail;

    @FXML
    private Text accountUsername;

    @FXML
    private VBox userReservationInfoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountId.setText(Integer.toString(MainApp.user.getUserId()));
        accountRole.setText(MainApp.user.getRole().getRoleName());
        accountEmail.setText(MainApp.user.getEmail());
        accountUsername.setText(MainApp.user.getUserName());
        try {
            ArrayList<UserReservationInfo> reservations = ServerCommunication.getUserReservationInfo(MainApp.user.getUserId());
            userReservationInfoList.getChildren().clear();
            for (UserReservationInfo uri: reservations) {
                displayUserReservationInfo(uri);
            }
            displayBikeReservationInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method creates a list of all the room reservations and adds them to the Vbox.
     * @author Kanish Dwivedi
     * @param uri - the UserReservationInfo object which is to be displayed.
     */
    public void displayUserReservationInfo(UserReservationInfo uri) {

        Text information = new Text("Day: " + uri.getDay() + " ,StartTime: " + uri.getStartTime() + ", EndTime: " + uri.getEndTime()
            + ", Building: " + uri.getBuildingName() + ", Room: " + uri.getRoomName() + " (" + uri.getName() + ") " + "\n Unique reservationID: " + uri.getReservationID());
        HBox reservationinfo = new HBox(information);

        int id = uri.getReservationID();

        Button deleteButton = new Button("-");
        deleteButton.setAlignment(Pos.TOP_RIGHT);
        reservationinfo.setPadding(new Insets(10, 0, 10, 0));
        HBox container =  new HBox(reservationinfo, deleteButton);
        userReservationInfoList.getChildren().add(container);

        deleteButton.setOnAction(event -> {
            try {
                deletePopUp(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
            HBox reservationinfo = new HBox(t);
            Button deleteButton = new Button("-");
            deleteButton.setAlignment(Pos.TOP_RIGHT);

            String[] res = s.split(",");
            String resId = res[0];
            String[] res2 = resId.split(": ");
            String resIdString = res2[1];
            int id = Integer.parseInt(resIdString);

            reservationinfo.setPadding(new Insets(10, 0, 10, 0));
            HBox container =  new HBox(reservationinfo, deleteButton);
            userReservationInfoList.getChildren().add(container);

            deleteButton.setOnAction(event -> {
                try {
                    deletePopUp(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    @FXML
    public void logoutButtonController(MouseEvent mouseEvent) throws IOException {
        MainApp.user = null;
        switchScene(mouseEvent, "/Welcome.fxml", "Welcome to the application");
    }

    @FXML
    public void bckButtonController(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "Tu Delft Reservation Application");
    }

    @FXML
    public void deletePopUp(int id) throws IOException {
        DeleteReservationPopUpController.id = id;
        Parent root = FXMLLoader.load(getClass().getResource("/deleteReservationPopUp.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }


}
