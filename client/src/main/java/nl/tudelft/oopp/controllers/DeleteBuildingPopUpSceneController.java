package nl.tudelft.oopp.controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.communication.ServerCommunication;



public class DeleteBuildingPopUpSceneController implements Initializable {

    @FXML private Text buildingName;
    @FXML private Text nonResSpace;
    @FXML private Text carParkingSpace;
    @FXML private Text description;
    @FXML private Text openTime;
    @FXML private Text closeTime;

    @FXML private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildingName.setText(DeleteBuildingSceneController.getBuildingName());
        if (DeleteBuildingSceneController.isNonResSpace()) {
            nonResSpace.setText("true");
        } else {
            nonResSpace.setText("false");
        }
        carParkingSpace.setText(DeleteBuildingSceneController.getCarParkingSpace());
        description.setText(DeleteBuildingSceneController.getDescription());
        openTime.setText(DeleteBuildingSceneController.getOpenTime());
        closeTime.setText(DeleteBuildingSceneController.getCloseTime());
    }

    @FXML
    public void cancelButtonHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * calls servercommunication to delete the building.
     * @throws URISyntaxException exception if URI syntax is incorrect.
     * @author Scott.
     */
    @FXML
    public void confirmButtonHandler() throws URISyntaxException {
        String name = buildingName.getText();

        ServerCommunication.deleteBuilding(name);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
