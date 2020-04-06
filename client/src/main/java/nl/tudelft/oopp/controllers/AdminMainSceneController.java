package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;

public class AdminMainSceneController implements Initializable {

    @FXML
    private Text username;

    @FXML
    private Text adminText;

    private static int status;

    public static void setStatus(int newStatus) {
        status = newStatus;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        if (status == 1) {
            adminText.setText("Successfully added a room!");
            setStatus(0);
        }

        if (status == 2) {
            adminText.setText("Successfully added a building!");
            setStatus(0);
        }

        if (status == 3) {
            adminText.setText("Successfully deleted a room!");
            setStatus(0);
        }

        if (status == 4) {
            adminText.setText("Successfully deleted a building!");
            setStatus(0);
        }
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

    /**
     * Button handler for the account button.
     * @param mouseEvent - the event created by the button
     * @throws IOException - exception thrown if file doesn't exist
     */
    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/accountPopupScene.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 232, 208);
        st.setScene(sc);
        st.show();
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
