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

public class DeleteRoomPopUpSceneController implements Initializable {

    @FXML private Text roomName;
    @FXML private Text capacity;
    @FXML private Text buildingName;
    @FXML private Text typeName;

    @FXML private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomName.setText(DeleteRoomSceneController.getRoomName());
        capacity.setText("" + DeleteRoomSceneController.getCapacity());
        buildingName.setText(DeleteRoomSceneController.getBuildingName());
        typeName.setText(DeleteRoomSceneController.getTypeName());
    }

    @FXML
    public void cancelButtonHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    /**
     * calls servercommunication to delete the room.
     * @throws URISyntaxException exception if URI syntax is incorrect.
     * @author Scott.
     */
    @FXML
    public void confirmButtonHandler() throws URISyntaxException {
        ServerCommunication.deleteRoom(DeleteRoomSceneController.getRoomId());
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
