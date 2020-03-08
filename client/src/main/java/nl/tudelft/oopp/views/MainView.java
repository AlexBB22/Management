package nl.tudelft.oopp.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
    private static Stage pStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/Welcome.fxml"));

        primaryStage.setTitle("Welcome to the application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        pStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }
}
