package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;

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
            selectBuildingBox.getItems().add(b.getBuilding_Name() + " #bikes: " + b.getBikes().size());
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
     * @throws IOException input/output exception
     * @author Scott.
     */
    public void addBikesToBuilding(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException, IOException {
        if (bikeInputField.getText() == null || selectBuildingBox.getValue() == null) {
            responseText.setText("Please kindly fill out all values");
        }
        int numberBikes = Integer.parseInt(bikeInputField.getText());

        String[] buildingName = selectBuildingBox.getValue().split(" ");
        String realBuildingName = buildingName[0];

        if (ServerCommunication.createBikes(numberBikes, realBuildingName)) {
            AdminMainSceneController.setStatus(5);
            closeScene();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/adminMainScene.fxml"));
            Parent root = loader.load();
            MainView.getPrimaryStage().setScene(new Scene(root));
        }

    }
}
