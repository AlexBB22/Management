package nl.tudelft.oopp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.communication.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nl.tudelft.oopp.MainApp.switchScene;

public class RestaurantSceneController implements Initializable {
    @FXML private Text username;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> buildingComboBox;
    @FXML private ComboBox<String> timeSlotComboBox;
    @FXML private ComboBox<String> restaurantComboBox;
    @FXML private Text selectBuildingMessage;
    @FXML private Text selectFoodBuildingMessage;
    @FXML private VBox foodList;

    private static String foodName;
    private static int price;

    public static String getFoodName() {
        return foodName;
    }

    public static int getPrice() {
        return price;
    }

    //This arrayList just saves all the buildings from the query made during initialisation
    private ArrayList<Building> buildingList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(MainApp.user.getUserName());
        ArrayList<Building> buildings = null;
        try {
            buildings = ServerCommunication.getBuildings();
            buildingList = buildings;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Building b : buildings) {
            buildingComboBox.getItems().add(b.getBuilding_Name());
        }
        //hide timeslot combobox
        timeSlotComboBox.setDisable(true);
        selectBuildingMessage.setText("Select a building first please");

        //hide food combobox
        restaurantComboBox.setDisable(true);
        selectFoodBuildingMessage.setText("Select a building first please");
    }

    /**
     * Whenever a building is selected from the dropdown menu
     * depending on the building, we give different start and end times.
     *
     * @param actionEvent - event that describes a selection of a value from the buildingComboBox
     */
    @FXML
    public void getBuildingTimesAndRestaurants(ActionEvent actionEvent) throws IOException, URISyntaxException {
        //Setting timSlotComboBox to have correct values
        String buildingName = buildingComboBox.getValue();
        Time startTime = null;
        Time endTime = null;
        for (Building b : buildingList) {
            String buName = b.getBuilding_Name();
            if (buName.equals(buildingName)) {
                startTime = b.getOpening();
                System.out.println(b.getBuilding_Name() + " " + startTime);
                endTime = b.getClosing();
            }
        }

        ObservableList<String> times = FXCollections.observableArrayList();
        //add specific start time if its not the regular one
        if (!startTime.toString().equals("08:45:00")) {
            String t1 = startTime + "-" + "08:45:00";
            times.add(t1);
        }
        //add regular times
        String t2 = "08:45:00-10:45:00";
        String t3 = "10:45:00-12:45:00";
        String t4 = "13:45:00-15:45:00";
        String t5 = "15:45:00-17:45:00";
        times.addAll(t2, t3, t4, t5);

        //add specific end time if its not the regular one
        if (!endTime.toString().equals("17:45:00")) {
            String t6 = "17:45:00" + "-" + endTime;
            times.add(t6);
        }
        //first clear out items in combobox then add new set
        timeSlotComboBox.getItems().clear();
        timeSlotComboBox.getItems().addAll(times);

        timeSlotComboBox.setDisable(false);
        selectBuildingMessage.setText("");

        ArrayList<Restaurant> restaurants = ServerCommunication.getRestaurants(buildingName);
        ObservableList<String> restaurantNames = FXCollections.observableArrayList();
        for(int i = 0; i < restaurants.size(); i++) {
            int id = restaurants.get(i).getResId();
            restaurantNames.add(Integer.toString(id));
        }

        //first clear out items in combobox then add new set
        restaurantComboBox.getItems().clear();
        restaurantComboBox.getItems().addAll(restaurantNames);

        //Enable food combobox
        restaurantComboBox.setDisable(false);
        selectFoodBuildingMessage.setText("");
    }

    @FXML
    public void backBtnHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/mainScene.fxml", "TuDelft Reservation Application");
    }

    @FXML
    public void orderBtnHandler(ActionEvent actionEvent) throws IOException, URISyntaxException {
        String resIdString = restaurantComboBox.getValue();
        int resId = Integer.parseInt(resIdString);
        ArrayList<Food> foods = ServerCommunication.getFoods(resId);

        // Clear vbox before adding all the food items into it
        foodList.getChildren().clear();

        //Making the title "This restaurants menu" and adding it to the main Vbox
        Text availableRoomTitle = new Text("This restaurants menu");
        availableRoomTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        availableRoomTitle.setFill(Color.BLUE);
        foodList.getChildren().add(availableRoomTitle);

        //Getting the start and end time the user selected to make query to DB
        String[] timeSlot = timeSlotComboBox.getValue().split("-");
        String starttime = timeSlot[0];
        String endtime = timeSlot[1];

        if (foods.size() == 0) {
            Text info = new Text("No available food for this restaurant.");
            info.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            foodList.getChildren().add(info);
            foodList.setMargin(info, new Insets(5, 0, 10, 15));
        } else {
        //Calling method createFoodView with each food option so that its shown to the user and added into the vbox
            for (Food fd: foods) {
                createFoodView(fd);
            }
        }
    }

    @FXML
    public void accountButtonHandler(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "/accountScene.fxml", "Account settings");
    }

    public void createFoodView(Food fd) throws FileNotFoundException {
        //Making outermost box
        HBox mainBox = new HBox();
        mainBox.setPrefHeight(160);
        mainBox.setPrefWidth(560);
        mainBox.setStyle("-fx-border-width: 2; -fx-border-color:  #2ad8ff;");

        //image Hbox
        HBox imgBox = new HBox();
        imgBox.setPrefWidth(200);
        imgBox.setPrefHeight(150);
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setNodeOrientation(NodeOrientation.INHERIT);

        Image image = getCorrectImage(fd.getFoodId());
        ImageView imV = new ImageView();
        imV.setImage(image);
        imV.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        imV.setFitWidth(180);
        imV.setFitHeight(135);
        imgBox.getChildren().add(imV);
        imgBox.setMargin(imV, new Insets(5, 5, 5, 5));

        //information VBox
        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setNodeOrientation(NodeOrientation.INHERIT);
        infoBox.setPrefHeight(145);
        infoBox.setPrefWidth(360);

        HBox foodNameBox = new HBox();
        foodNameBox.setAlignment(Pos.CENTER_LEFT);
        foodNameBox.setPrefHeight(25);
        Text foodName = new Text(fd.getFoodId());
        foodName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        foodName.setFill(Color.BLACK);
        foodNameBox.getChildren().add(foodName);

        //this second one adds the price of the food
        HBox foodPrice = new HBox();
        foodPrice.setAlignment(Pos.TOP_LEFT);
        foodPrice.setPrefHeight(27);
        Text Price = new Text("\t price: €" + fd.getPrice());
        Price.setFont(Font.font("Arial", 16));
        foodPrice.getChildren().add(Price);

        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.BOTTOM_LEFT);
        imageBox.setPrefHeight(30);

        Button orderButton = new Button("Order!");
        orderButton.setPrefSize(345, 30);
        orderButton.setMaxSize(345, 30);
        orderButton.setStyle("-fx-background-color: #2f93ff; -fx-text-fill: white; -fx-text-alignment: center; "
                + "-fx-font-family: 'Arial'; -fx-font-size: 13px; -fx-font-weight: bold;");
        orderButton.setOnAction(event -> {
            try {
                reservePopUp(fd.getFoodId(), fd.getPrice());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        orderButton.setCursor(Cursor.HAND);
        imageBox.getChildren().add(orderButton);
        imageBox.setMargin(orderButton, new Insets(0, 5, 0, 5));

        infoBox.getChildren().addAll(foodNameBox, foodPrice, imageBox);




        mainBox.getChildren().add(imgBox);
        mainBox.setMargin(imgBox, new Insets(0, 0, 0, 0));
        mainBox.getChildren().add(infoBox);
        mainBox.setMargin(infoBox, new Insets(5, 0, 5, 0));

        //add mainBox to the outermost VBox that contains all these room reservation Hboxes
        foodList.getChildren().add(mainBox);
        foodList.setMargin(mainBox, new Insets(5, 18, 5, 0));
    }

    public Image getCorrectImage(String foodType) {
        Image resImg = null;
        if (foodType.equals("StudyRoom")) {
            resImg = new Image("images/studyRoom.jpg");
        }
        if (foodType.equals("ProjectRoom")) {
            resImg = new Image("images/projectRoom.jpg");
        }
        if (foodType.equals("LectureHall")) {
            resImg = new Image("images/lectureHall.jpg");
        }
        if (foodType.equals("StudyHall")) {
            resImg = new Image("images/studyHall.jpg");
        }
        return resImg;
    }

    public void reservePopUp(String foodName, int price) throws IOException {
        //Setting static variables to properties given so that these can be accessed in the other controller class
        RestaurantSceneController.foodName = foodName;
        RestaurantSceneController.price = price;

        Parent root = FXMLLoader.load(getClass().getResource("/restaurantPopUpScene.fxml"));
        Stage st = new Stage();
        Scene sc = new Scene(root, 300, 400);
        st.setScene(sc);
        st.show();
    }
}