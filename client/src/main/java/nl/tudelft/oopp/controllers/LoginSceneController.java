package nl.tudelft.oopp.controllers;

import com.sun.tools.javac.Main;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
     * Handle submit button. It peforms the action of extracting user name and password from the input fields
     * then sends those to the Server to verify.
     * @param event - The event object that contains information about fired event.
     * @throws URISyntaxException - Exception thrown if server communication failed with given username and password
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
     * Handle new user button. Switches scene to allow user to create a new account.
     * @throws IOException - Exception thrown when switch fails (eg. wrong fxml file name)
     */
    @FXML
    public void newUserButtonHandler(ActionEvent event) throws IOException {
        MainApp.switchScene(event, "/newUserScene.fxml", "Create an account");
    }

    /**
     * Handle back button. Switches scene to allow user to go back to the welcome scene.
     * @throws IOException - Exception thrown when switch fails (eg. wrong fxml file name)
     */
    @FXML
    public void backToWelcome(MouseEvent event) throws IOException {
        MainApp.switchScene(event, "/Welcome.fxml", "Welcome to the application");
    }

}
