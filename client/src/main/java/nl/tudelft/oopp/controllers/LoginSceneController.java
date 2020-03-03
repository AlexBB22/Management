package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.User;

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
    public void signInButtonHandler(ActionEvent event) throws URISyntaxException {
        String username = usernameField.getText();
        String password = passwordfield.getText();

        //Now communicating with server to see if user exists in database. If all went well, we will get back the
        //user object from the DB that represents the user that has just logged in
        User loggedInUser = ServerCommunication.identifyUser(username, password);

        if (loggedInUser == null) {
            submitResponse.setText("Please enter credentials again");
        } else {
            try {
                //Setting the global user variable to the newUser that just logged in
                MainApp.user = loggedInUser;
                MainApp.switchScene(event, "/mainScene.fxml");
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
        MainApp.switchScene(event, "/newUserScene.fxml", "Create an account");
    }

}
