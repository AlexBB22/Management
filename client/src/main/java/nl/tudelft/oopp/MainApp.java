package nl.tudelft.oopp.demo;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.MainSceneController;
import nl.tudelft.oopp.demo.views.MainView;

import java.io.IOException;
import java.util.Optional;

public class MainApp {
    public static void main(String[] args) {
        MainView.main((new String[0]));
    }

    /**
     * Function that can switch scene
     * @param actionEvent
     * @param source
     * @throws IOException
     */
    public static void switchScene(Event actionEvent, String source, String... title) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(source));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));

        if (title != null) {
            stage.setTitle(title[0]);
        }
    }

}
