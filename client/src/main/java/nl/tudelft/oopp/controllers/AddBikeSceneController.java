package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.ServerCommunication;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddBikeSceneController implements Initializable {

    @FXML private TextField bikeInputField;
    @FXML private ImageView backButton;
    @FXML private ComboBox<String> selectBuildingBox;
    @FXML private Text responseText;
    private ArrayList<Building> buildingList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Building> buildings = null;
        try {
            buildings = ServerCommunication.getBuildings();
            this.buildingList = buildings;
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        for (Building b : buildingList) {
            selectBuildingBox.getItems().add(b.getBuilding_Name());
        }
    }

    @FXML
    public void closeScene() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * add bikes to building.
     * @param mouseEvent the clicking of the mouse on the button.
     * @throws URISyntaxException throws exception if url syntax is invalid.
     * @author Scott.
     */
    public void addBikesToBuilding(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException {
        if (bikeInputField.getText() == null || selectBuildingBox.getValue() == null) {
            responseText.setText("Please kindly fill out all values");
        }
        int numberBikes = Integer.parseInt(bikeInputField.getText());

        String[] nameArray = selectBuildingBox.getValue().split(" ");
        String buildingName = nameArray[0];
        for (int i = 1; i < nameArray.length; i++) {
            buildingName = buildingName + "_" + nameArray[i];
        }

        if (ServerCommunication.createBikes(numberBikes, buildingName)) {
            closeScene();
        }
    }
}
