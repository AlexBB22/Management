package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;

public class AdminMainSceneController implements Initializable {

    @FXML
    private Text username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/accountScene.fxml", "Account Settings");
    }

    @FXML
    public void createBuildingButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/addBuildingScene.fxml", "Create Building");
    }

    @FXML
    public void createRoomButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/addRoomScene.fxml", "Create Room");
    }


    @FXML
    public void deleteBuildingButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/deleteBuildingScene.fxml", "Delete Building");
    }

    @FXML
    public void deleteRoomButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/deleteRoomScene.fxml", "Delete Room");
    }
}
