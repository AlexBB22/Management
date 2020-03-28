package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.views.MainView;


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

    /**
     * Logout button handler that closes popup and goes back to the welcome scene.
     * @param mouseEvent - the mouse event created by the logout button
     * @throws IOException - throws exception if file does not exist
     * @author Hidde Agterberg
     */
    @FXML
    public void logoutBtnHandler(MouseEvent mouseEvent) throws IOException {
        MainApp.user = null;

        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/Welcome.fxml"));
        Parent root = loader.load();
        MainView.getPrimaryStage().setScene(new Scene(root));
    }

}
