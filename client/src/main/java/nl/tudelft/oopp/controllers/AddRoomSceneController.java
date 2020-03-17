package nl.tudelft.oopp.controllers;
import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class AddRoomSceneController {

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
    }
}
