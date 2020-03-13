package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.calendarfx.view.page.DayPage;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.tudelft.oopp.MainApp;


public class MainSceneController implements Initializable {

    @FXML
    private Text username;

    @FXML
    private Text res;

    private static int status = 0;

    @FXML
    private DayPage dayPage;

    @FXML
    private GridPane mainGrid;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //adding clock
        Tile clockTile = TileBuilder.create().skinType(Tile.SkinType.CLOCK)
                .prefSize(200, 200).locale(Locale.ENGLISH).dateVisible(false).running(true).build();
        mainGrid.add(clockTile, 1, 1);

        Tile dateTile = TileBuilder.create().skinType(Tile.SkinType.DATE).backgroundColor(Color.BLUE).build();
        mainGrid.add(dateTile, 2, 1);
        username.setText(MainApp.user.getUserName());
        if (status == 1) {
            changeResConfirmed();
            setStatus(0);
        }

    }

    @FXML
    public void reserveRoomButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/roomReservationScene.fxml", "Reserve a room");
    }

    @FXML
    public void rentBikeButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/?.fxml");
    }

    @FXML
    public void carParkButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/?.fxml");
    }

    @FXML
    public void restaurantButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/?.fxml");
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/accountScene.fxml", "Account Settings");
    }

    public void changeResConfirmed() {
        res.setText("The room has been successfully reserved!");
    }

    public static void setStatus(int newStatus) {
        status = newStatus;
    }



}
