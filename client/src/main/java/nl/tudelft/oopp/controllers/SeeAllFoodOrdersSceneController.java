package nl.tudelft.oopp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.FoodReservation;
import nl.tudelft.oopp.communication.ServerCommunication;
import nl.tudelft.oopp.communication.UserReservationInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nl.tudelft.oopp.MainApp.switchScene;

public class SeeAllFoodOrdersSceneController implements Initializable {
    @FXML
    private VBox foodOrderInfoList;
    private static int reservationID;
    private static int status;

    public static int getReservationID(){
        return reservationID;
    }

    public static int getStatus() {
        return status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SeeAllFoodOrdersSceneController.status = 0;
        try {
            ArrayList<FoodReservation> orders = ServerCommunication.getFoodReservations();
            foodOrderInfoList.getChildren().clear();
            for (FoodReservation fr: orders) {
                System.out.println("here");
                displayFoodReservationInfo(fr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        reservationID = -1;
    }


    /**
     * This method creates a list of all the food orders and adds them to the Vbox.
     * @author Kanish Dwivedi
     * @param fr - the FoodReservation object which is to be displayed.
     */
    public void displayFoodReservationInfo(FoodReservation fr) {

        Text information = new Text("\nUnique Order ID: " + fr.getReservationId() +
                "\nFood: " + fr.getFoodFk().getName() +
                "\nRestaurant: " + fr.getRestaurantFk().getRestaurantName() +
                "\nDay: " + fr.getDay()  + "\nStartTime: " + fr.getStartTime() + "\nEndTime: " + fr.getEndTime() +
               "\n\n");
        information.setTextAlignment(TextAlignment.CENTER);
        information.setFont(Font.font("Chalkboard SE", 16));
        HBox reservationinfo = new HBox(information);

        reservationinfo.setStyle("-fx-border-width:3;");
        reservationinfo.setStyle("-fx-border-color:blue;");
        reservationinfo.setStyle("-fx-border-style: solid;");
        reservationinfo.setStyle("-fx-background-color: #99ebff;");


        Button deleteButton = new Button("Remove");
        deleteButton.setStyle("-fx-base: blue");

        deleteButton.setAlignment(Pos.CENTER_LEFT);
        reservationinfo.setPadding(new Insets(10, 0, 10, 0));
        HBox container =  new HBox(reservationinfo, deleteButton);
        container.setStyle("-fx-border-width:3;");
        container.setStyle("-fx-border-color:blue;");
        container.setStyle("-fx-border-style: solid;");
        container.setAlignment(Pos.CENTER);

        foodOrderInfoList.getChildren().add(container);

        int id = fr.getReservationId();
        System.out.println(id);
        deleteButton.setOnAction(event -> {
            try {
                System.out.println(id);
                deletePopUp(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method starts the pop up.
     * @param id - id of the order to be deleted
     * @throws IOException - throws exception if the file is not found
     */
    @FXML
    public void deletePopUp(int id) throws IOException {
        SeeAllFoodOrdersSceneController.reservationID = id;
        SeeAllFoodOrdersSceneController.status = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/deleteReservationPopUp.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }

    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

}
