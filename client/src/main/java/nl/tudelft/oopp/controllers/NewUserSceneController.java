package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Role;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.User;


public class NewUserSceneController implements Initializable {
    @FXML private Text submitResponse;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private ComboBox<String> roleComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Adding possible role selections to the combo box
        Role admin = new Role(4, "Admin");
        Role teacher = new Role(3, "Teacher");
        Role staff = new Role(2, "Staff");
        Role student = new Role(1, "Student");
        roleComboBox.getItems().addAll(admin.getRoleName(), teacher.getRoleName(), staff.getRoleName(), student.getRoleName());

        passwordField.setFocusTraversable(false);
        emailField.setFocusTraversable(false);
        usernameField.setFocusTraversable(false);
    }

    /**
     * Handle new user button. This method extracts data that the user inputted from the GUI, then communicates
     * with the server to create a new user.
     * @throws URISyntaxException - Exception thrown when URL formed is invalid.
     */
    @FXML
    public void createNewUserButtonHandler(ActionEvent event) throws URISyntaxException, IOException {
        String rolename = roleComboBox.getValue();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        System.out.println(password.length());


        //Setting submitResponse to appropriate cases:
        if (rolename == null || username == null || email == null) {
            submitResponse.setText("");
            submitResponse.setText("Please fill out all fields");
            return;
        }

        if (password.length() < 8) {
            submitResponse.setText("");
            submitResponse.setText("Please enter a password with more than 8 characters");
            return;
        }

        if (!email.contains("@")) {
            submitResponse.setText("");
            submitResponse.setText("Please use a valid email");
            return;
        }


        submitResponse.setText("Thanks for your info, give us a moment to verify");

        //get roleId that corresponds to the role the user selected
        int roleId = 1;
        if (rolename.equals("Admin")) {
            roleId = 4;
        }
        if (rolename.equals("Teacher")) {
            roleId = 3;
        }
        if (rolename.equals("Staff")) {
            roleId = 2;
        }
        int success = ServerCommunication.createUser(username, email, password, roleId);

        //the returned user is null if the client entered an existing username
        if (success == -1) {
            submitResponse.setText("This user name is already taken, please try again");
        } else {
            submitResponse.setText("Accepted, welcome. Please log in");
            try {
                MainApp.switchScene(event, "/loginScene.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void backToLogin(Event event) throws IOException {
        switchScene(event, "/loginScene.fxml", "Login");
    }

}
