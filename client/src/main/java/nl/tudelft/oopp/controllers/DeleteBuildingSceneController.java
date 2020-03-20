package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.ServerCommunication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nl.tudelft.oopp.MainApp.switchScene;

public class DeleteBuildingSceneController implements Initializable {

    @FXML private Text username;
    @FXML private ScrollPane deleteScrollPane;
    @FXML private VBox VBoxInScrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        deleteScrollPane.setContent(VBoxInScrollPane);
        ArrayList<Building> buildings = null;


        try {
            buildings = ServerCommunication.getBuildings();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        GridPane gridPane = new GridPane();
        for (Building b : buildings) {
            Button deleteBtn = new Button("Delete");
            deleteBtn.setAlignment(Pos.CENTER_LEFT);
            deleteBtn.setOnAction(event -> {
                try {
                    ServerCommunication.deleteBuilding(b.getBuilding_Name());
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setContentText("Building deleted successfully");
                    warning.show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            gridPane.add(new Text(b.getBuilding_Name()), 0, i);
            i = i + 1;
            gridPane.add(new Text(b.getDescription() + " car parking spaces: " + b.getCar_parking_spaces() + " has non reservable space: " + b.isNon_reservable_space()), 0, i);
            i = i + 1;
            gridPane.add(new Text("opening time: " + b.getOpening() + " closing time: " + b.getClosing()), 0, i);
            gridPane.add(deleteBtn, 1, i);
            i = i + 1;
        }
        deleteScrollPane.setContent(gridPane);
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
    }
}
