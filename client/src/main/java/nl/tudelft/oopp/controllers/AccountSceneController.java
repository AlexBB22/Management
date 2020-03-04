package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;


public class AccountSceneController implements Initializable {

    @FXML
    private Text accountId;

    @FXML
    private Text accountRole;

    @FXML
    private Text accountEmail;

    @FXML
    private Text accountUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountId.setText(Integer.toString(MainApp.user.getUserId()));
        accountRole.setText(MainApp.user.getRole().getRoleName());
        accountEmail.setText(MainApp.user.getEmail());
        accountUsername.setText(MainApp.user.getUserName());
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
}
