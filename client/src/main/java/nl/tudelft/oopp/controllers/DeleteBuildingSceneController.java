package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.Building;
import nl.tudelft.oopp.communication.ServerCommunication;



public class DeleteBuildingSceneController implements Initializable {

    @FXML private Text username;
    @FXML private ScrollPane deleteScrollPane;
    @FXML private VBox vboxInScrollPane;

    private ArrayList<Building> buildingList;

    private static String buildingName;
    private static boolean nonResSpace;
    private static int carParkingSpace;
    private static String description;
    private static String openTime;
    private static String closeTime;

    public static String getBuildingName() {
        return buildingName;
    }

    public static boolean isNonResSpace() {
        return nonResSpace;
    }

    public static String getCarParkingSpace() {
        return "" + carParkingSpace;
    }

    public static String getDescription() {
        return description;
    }

    public static String getOpenTime() {
        return openTime;
    }

    public static String getCloseTime() {
        return closeTime;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        deleteScrollPane.setContent(vboxInScrollPane);
        ArrayList<Building> buildings = null;

        try {
            buildings = ServerCommunication.getBuildings();
            buildingList = buildings;
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
            deleteBtn.setStyle("-fx-background-color: rgb(255, 151, 98); -fx-text-fill: rgb(0, 0, 0); -fx-border-radius: 50px; -fx-background-radius: 50px;");
            deleteBtn.setOnAction(event -> {
                try {
                    DeleteBuildingSceneController.buildingName = b.getBuilding_Name();
                    DeleteBuildingSceneController.nonResSpace = b.isNon_reservable_space();
                    DeleteBuildingSceneController.carParkingSpace = b.getCar_parking_spaces();
                    DeleteBuildingSceneController.description = b.getDescription();
                    DeleteBuildingSceneController.openTime = b.getOpening().toString();
                    DeleteBuildingSceneController.closeTime = b.getClosing().toString();
                    Parent root = FXMLLoader.load(getClass().getResource("/deleteBuildingPopUpScene.fxml"));
                    Stage st = new Stage();
                    Scene sc = new Scene(root, 488, 258);
                    st.setScene(sc);
                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Text buildingName = new Text(b.getBuilding_Name());
            buildingName.setFont(Font.font(20));

            gridPane.add(buildingName, 0, i);
            i = i + 1;
            gridPane.add(new Text(b.getDescription() + " car parking spaces: " + b.getCar_parking_spaces() + " has non reservable space: " + b.isNon_reservable_space()), 0, i);
            i = i + 1;
            gridPane.add(new Text("opening time: " + b.getOpening() + " closing time: " + b.getClosing()), 0, i);
            gridPane.add(deleteBtn, 1, i);
            i = i + 1;
            gridPane.add(new Text(""), 0, i);
            i = i + 1;
        }
        deleteScrollPane.setContent(gridPane);
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/adminMainScene.fxml", "Admin Window");
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
}
