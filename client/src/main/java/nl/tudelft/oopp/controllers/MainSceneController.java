package nl.tudelft.oopp.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static nl.tudelft.oopp.demo.MainApp.switchScene;

public class MainSceneController implements Initializable {

    @FXML
    private Text username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void reserveRoomButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/roomReservationScene.fxml");
    }

    @FXML
    public void rentBikeButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/?.fxml");
    }

    @FXML
    public void carParkButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/?.fxml");
    }

    @FXML
    public void restaurantButtonHandler(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/?.fxml");
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/?.fxml");
    }

}
