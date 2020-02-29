package nl.tudelft.oopp.demo.controllers;

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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import nl.tudelft.oopp.demo.communication.ServerCommunication;

import static nl.tudelft.oopp.demo.MainApp.switchScene;

public class LoginSceneController implements Initializable {
    @FXML private Text submitResponse;
    @FXML private TextField passwordfield;
    @FXML private TextField usernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Handle submit button
     * @param event
     * @throws URISyntaxException
     */
    @FXML
    public void submitButtonHandler(ActionEvent event) throws URISyntaxException {
        String username = usernameField.getText();
        String password = passwordfield.getText();

        //Now communicating with server to see if user exists in database
        boolean isUser = ServerCommunication.identifyUser(username, password);

        if (isUser == false) {
            submitResponse.setText("Please enter credentials again");
        } else {
            try {
                switchScene(event, "/mainScene.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle new user button
     * @throws IOException
     */
    @FXML
    public void newUserButtonHandler(ActionEvent event) throws IOException {
        switchScene(event, "/newUserScene.fxml", "Create an account");
    }

}
