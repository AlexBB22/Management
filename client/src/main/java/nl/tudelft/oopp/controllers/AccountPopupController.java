package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;

import static nl.tudelft.oopp.MainApp.switchScene;
import static nl.tudelft.oopp.MainApp.user;

public class AccountPopupController implements Initializable {

    @FXML private Label username;
    @FXML private Label email;
    @FXML private Label role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        email.setText(MainApp.user.getEmail());
        role.setText(MainApp.user.getRole().getRoleName());
    }

    @FXML
    public void logoutBtnHandler(MouseEvent mouseEvent) throws IOException {
        MainApp.user = null;

        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();

        switchScene(mouseEvent, "/Welcome.fxml", "Welcome to the application");
    }

}
