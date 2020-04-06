package nl.tudelft.oopp.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.views.MainView;

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
     * @throws IOException input/output exception
     * @author Scott.
     */
    @FXML
    public void confirmButtonHandler() throws URISyntaxException, IOException {
        ServerCommunication.deleteRoom(DeleteRoomSceneController.getRoomId());
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/adminMainScene.fxml"));
        Parent root = loader.load();
        AdminMainSceneController.setStatus(3);
        MainView.getPrimaryStage().setScene(new Scene(root));
    }
}
