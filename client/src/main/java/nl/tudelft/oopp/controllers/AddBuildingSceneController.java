package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.ServerCommunication;


public class AddBuildingSceneController implements Initializable {

    @FXML private VBox vboxInScrollPane;
    @FXML private Text username;
    @FXML private TextField buildingNameTextField;
    @FXML private CheckBox nonResSpaceCheckBox;
    @FXML private TextField carParkingSpaceTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField openTimeTextField;
    @FXML private TextField closeTimeTextField;
    @FXML private ScrollPane buildingScrollPane;
    @FXML private Text confirmationText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        buildingScrollPane.setContent(vboxInScrollPane);
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

            gridPane.add(new Text(b.getBuilding_Name()), 0, i);
            i = i + 1;
            gridPane.add(new Text(b.getDescription() + " car parking spaces: " + b.getCar_parking_spaces() + " has non reservable space: " + b.isNon_reservable_space()), 0, i);
            i = i + 1;
            gridPane.add(new Text("opening time: " + b.getOpening() + " closing time: " + b.getClosing()), 0, i);
            i = i + 1;
            gridPane.add(new Text(""), 0, i);
            i = i + 1;

        }
        buildingScrollPane.setContent(gridPane);
    }

    /**
     * adds a building to the database.
     * @param mouseEvent the clicking of the mouse.
     * @author Scott.
     */
    @FXML
    public void addBuildingButtonHandler(MouseEvent mouseEvent) {
        try {
            //removing spaces so the description can be send in the url using _ to later identify where spaces should be.
            String spaceBuildingName = buildingNameTextField.getText();
            String[] buildingArray = spaceBuildingName.split(" ");
            String buildingName = buildingArray[0];
            for (int i = 1; i < buildingArray.length; i++) {
                buildingName = buildingName + "_" + buildingArray[i];
            }

            Boolean nonResSpace = nonResSpaceCheckBox.isSelected();
            int carParkingSpace = Integer.parseInt(carParkingSpaceTextField.getText());

            //removing spaces so the description can be send in the url using _ to later identify where spaces should be.
            String spaceDescription = descriptionTextField.getText();
            String[] descriptionArray = spaceDescription.split(" ");
            String description = descriptionArray[0];
            for (int i = 1; i < descriptionArray.length; i++) {
                description = description + "_" + descriptionArray[i];
            }

            Time openTime = Time.valueOf(openTimeTextField.getText());
            Time closeTime = Time.valueOf(closeTimeTextField.getText());
            ServerCommunication.createBuilding(buildingName, nonResSpace, carParkingSpace, description, openTime, closeTime);
            confirmationText.setText("Building added successfully.");

        } catch (Exception e) {
            confirmationText.setText("Building failed to add.");
            throw new IllegalArgumentException("invalid input");
        }
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
    }
}
