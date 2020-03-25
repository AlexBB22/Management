package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class DeleteReservationPopUpController {

    @FXML private Button cancelButton;

    @FXML public static int id;

    private Text resId;


    public void initialize(URL location, ResourceBundle resources) {
       resId.setText(Integer.toString(id));
    }

    @FXML
    void backBtnHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void deleteReservation(ActionEvent actionEvent) throws URISyntaxException, IOException {

        int okRoomCode = ServerCommunication.deleteRoomReservation(this.id);
        int okBikeCode = ServerCommunication.deleteBikeReservation(this.id);

        if (okBikeCode == -1) {
            if (okRoomCode == -1) {
                System.out.println("Something went wrong!");
                return;
            } else {
                backBtnHandler();
                MainSceneController.setStatus(3);
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/mainScene.fxml"));
                Parent root = loader.load();
                MainView.getPrimaryStage().setScene(new Scene(root));
            }
        }
        backBtnHandler();
        MainSceneController.setStatus(3);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/mainScene.fxml"));
        Parent root = loader.load();
        MainView.getPrimaryStage().setScene(new Scene(root));
    }
}


