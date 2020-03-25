package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;



public class DeleteReservationPopUpController implements Initializable {

    @FXML private Button cancelButton;

   private int id;

    @FXML private Text resId;


    public void initialize(URL location, ResourceBundle resources) {
        this.id = AccountSceneController.getReservationID();
        resId.setText(Integer.toString(this.id));
    }

    @FXML
    void backBtnHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This method is used to delete the selected reservations.
     * @param actionEvent - the event which activates the neet for this method
     * @throws URISyntaxException - exception thrown if syntax is incorrect
     * @throws IOException - exception thrown if the file is not found
     */
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


