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

public class LoginSceneController implements Initializable {
    @FXML private Text submitResponse;
    @FXML private TextField passwordfield;
    @FXML private TextField usernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**.
     * Handle submit button
     * @param event the event that happens
     * @throws URISyntaxException exception thrown when syntax is incorrect
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
                MainApp.switchScene(event, "/mainScene.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**.
     * Handle new user button
     * @param event the event that happens
     * @throws IOException throws an exception if something is wrong with the input or output
     */
    @FXML
    public void newUserButtonHandler(ActionEvent event) throws IOException {
        MainApp.switchScene(event, "/newUserScene.fxml", "Create an account");
    }

}
