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
        submitResponse.setText("Thanks for your info");
        String username = usernameField.getText();
        String password = passwordfield.getText();

        //Now communicating with server to see if user exists in database
        boolean isUser = ServerCommunication.identifyUser(username, password);
        if (isUser == false) {
            submitResponse.setText("Please enter credentials again");
        } else {
            submitResponse.setText("Accepted, welcome");
            try {
                loadSecond(event);
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
    public void newUserButtonHandler() throws IOException {
        // TODO: switch to the new user scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/newUserScene.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Load main scene
     * @param event
     * @throws IOException
     */
    @FXML
    public void loadSecond(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
