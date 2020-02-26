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
import javafx.stage.Stage;

public class MainSceneController implements Initializable {

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

    /**
     * Function that can switch scene
     * @param actionEvent
     * @param source
     * @throws IOException
     */
    public static void switchScene(Event actionEvent, String source) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainSceneController.class.getResource(source));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
