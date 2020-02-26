package nl.tudelft.oopp.demo.controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import nl.tudelft.oopp.demo.communication.ServerCommunication;

public class NewUserSceneController implements Initializable {
    @FXML private Text submitResponse;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void createNewUserButtonHandler() throws URISyntaxException {

        //submitResponse.setText("Thanks for your info");
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        boolean newUser = ServerCommunication.createUser(username, email, password);

        if (newUser == false) {
            submitResponse.setText("Please enter credentials again");
        } else {
            submitResponse.setText("Accepted, welcome");
            // TODO close window and go to login
        }
    }
}
