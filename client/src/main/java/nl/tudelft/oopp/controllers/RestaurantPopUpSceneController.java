package nl.tudelft.oopp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantPopUpSceneController implements Initializable {
    @FXML private Text reservationUserName;
    @FXML Text foodName;
    @FXML Text price;

    @FXML private Text resConfirmed;
    @FXML private Button cancelButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservationUserName.setText(MainApp.user.getUserName());
        foodName.setText(RestaurantSceneController.getFoodName());
        price.setText(String.valueOf(RestaurantSceneController.getPrice()));
    }

    @FXML
    void backBtnHandler() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void makeNewFoodOrder(ActionEvent event) throws URISyntaxException, IOException {

    }
}
