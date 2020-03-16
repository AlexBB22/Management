package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;


public class WelcomeSceneController implements Initializable {
    @FXML
    private Button logIn;

    @FXML
    private Button quitApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void logInView(ActionEvent event) throws IOException {
        MainApp.switchScene(event, "/loginScene.fxml", "Please log in");
    }

    @FXML
    void quitApplication(ActionEvent event) {
        Stage stage = (Stage) quitApp.getScene().getWindow();
        stage.close();
    }

}

