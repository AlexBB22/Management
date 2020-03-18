package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;

public class BikeReservationPopUpSceneController implements Initializable {
    @FXML
    private Text building;
    @FXML
    private Text day;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        building.setText(BikeReservationSceneController.getBuildingName());
        day.setText(BikeReservationSceneController.getDay());
    }

    /**This makes a new bike reservation.
     *
     * @param event what happens
     * @throws URISyntaxException exception
     * @throws IOException exception
     */
    @FXML
    public void makeNewBikeReservation(ActionEvent event) throws URISyntaxException, IOException {
        int okCode = ServerCommunication.createBikeReservation(building.getText(), Date.valueOf(day.getText()));

        if (okCode == -1) {
            System.out.println("Something went wrong!");
            return;
        }

        MainSceneController.setStatus(2);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/mainScene.fxml"));
        Parent root = loader.load();
        MainView.getPrimaryStage().setScene(new Scene(root));

    }
}
