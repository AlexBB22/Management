package nl.tudelft.oopp.controllers;

import static nl.tudelft.oopp.MainApp.switchScene;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.User;
import nl.tudelft.oopp.communication.UserReservationInfo;


public class MainSceneController implements Initializable {

    @FXML private Text username;
    @FXML private Text res;
    private static int status = 0;
    @FXML private GridPane mainGrid;
    @FXML private Text todayDay;
    @FXML private Text todayDateNumber;
    @FXML private Text todayMonthYear;
    @FXML private HBox topBar;
    @FXML private VBox sideMenu;

    //Text items for the dates for the week
    @FXML private Text thisWeekMondayDate;
    @FXML private Text thisWeekTuesdayDate;
    @FXML private Text thisWeekWednesdayDate;
    @FXML private Text thisWeekThursdayDate;
    @FXML private Text thisWeekFridayDate;

    //VBox's for each weekday that stores room reservations
    @FXML private VBox mondayRoomReservationBox;
    @FXML private VBox tuesdayRoomReservationBox;
    @FXML private VBox wednesdayRoomReservationBox;
    @FXML private VBox thursdayRoomReservationBox;
    @FXML private VBox fridayRoomReservationBox;

    //VBox's for each weekday that stores agendas
    @FXML private VBox mondayAgendaBox;
    @FXML private VBox tuesdayAgendaBox;
    @FXML private VBox wednesdayAgendaBox;
    @FXML private VBox thursdayAgendaBox;
    @FXML private VBox fridayAgendaBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setting todays date on the first box
        Calendar cal = Calendar.getInstance();
        String[] dateInfo = cal.getTime().toString().split(" ");
        todayDay.setText(dateInfo[0]);
        todayDateNumber.setText(dateInfo[2]);
        todayMonthYear.setText(dateInfo[1] + " " + dateInfo[5]);

        //setting the border colors
        topBar.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.rgb(238, 201, 210), Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(5), Insets.EMPTY)));

        sideMenu.setBorder((new Border(new BorderStroke(Color.BLACK, Color.rgb(255, 63, 23), Color.BLACK, Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(5), Insets.EMPTY))));

        if (status == 1) {
            changeResConfirmed();
            setStatus(0);
        }

        //setting the date for each individual week box
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> dates = new ArrayList<>();

        for (int i = Calendar.MONDAY; i <= Calendar.FRIDAY; i++) {
            cal.set(Calendar.DAY_OF_WEEK, i);
            Date date = cal.getTime();
            String dayString = dayFormat.format(date);
            dates.add(dayString);
        }
        thisWeekMondayDate.setText(dates.get(0));
        thisWeekTuesdayDate.setText(dates.get(1));
        thisWeekWednesdayDate.setText(dates.get(2));
        thisWeekThursdayDate.setText(dates.get(3));
        thisWeekFridayDate.setText(dates.get(4));
        username.setText(MainApp.user.getUserName());
        try {
            addUserReservations();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to show the user all room reservations it has made. It adds all room reservations the user
     * has into the correct day of the week "box" in the GUI.
     * @author Kanish Dwivedi
     * @throws IOException - exception thrown when there are errors in JackSon parsing of JSON sent from server side
     * @throws URISyntaxException - exception thrown when the request URL is invalid.
     */
    @FXML
    public void addUserReservations() throws IOException, URISyntaxException {
        ArrayList<UserReservationInfo> userReservations = ServerCommunication.getUserReservationInfo(MainApp.user.getUserId());
        for (UserReservationInfo uri: userReservations) {
            String rrStr = "\t" + uri.getBuildingName() + " " + uri.getRoomName() + " " + uri.getStartTime() + "-" + uri.getEndTime();
            Text rr = new Text(rrStr);
            rr.setFont(Font.font("Chalkboard SE", 15));
            rr.setBoundsType(TextBoundsType.LOGICAL);

            if (uri.getDay().equals(thisWeekMondayDate.getText())) {
                mondayRoomReservationBox.getChildren().add(rr);
                continue;
            }
            if (uri.getDay().equals(thisWeekTuesdayDate.getText())) {
                tuesdayRoomReservationBox.getChildren().add(rr);
                continue;
            }
            if (uri.getDay().equals(thisWeekWednesdayDate.getText())) {
                wednesdayRoomReservationBox.getChildren().add(rr);
                continue;
            }
            if (uri.getDay().equals(thisWeekThursdayDate.getText())) {
                thursdayRoomReservationBox.getChildren().add(rr);
                continue;
            }
            if (uri.getDay().equals(thisWeekFridayDate.getText())) {
                fridayRoomReservationBox.getChildren().add(rr);
                continue;
            }
        }

    }


    @FXML
    public void reserveRoomButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/roomReservationScene.fxml", "Reserve a room");
    }

    @FXML
    public void rentBikeButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/bikeReservation.fxml");
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
