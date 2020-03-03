package nl.tudelft.oopp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import nl.tudelft.oopp.MainApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    }

}

