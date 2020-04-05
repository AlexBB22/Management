package nl.tudelft.oopp;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.communication.User;
import nl.tudelft.oopp.views.MainView;



public class MainApp {

    /**
     * This static global variable user represents the user that is currently using the application.
     */
    public static User user;

    /**
     * Function that can performs a switch to the source fxml file url.
     * @param actionEvent - The event object that is fired.
     * @param source - The fxml file's url to which the switch is performed to.
     * @throws IOException - Exception thrown when file not found.
     */
    public static void switchScene(Event actionEvent, String source, String... title)
                                    throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(source));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));

        if (title.length > 0) {
            stage.setTitle(title[0]);
        }
    }

    public static void main(String[] args) {
        MainView.main((new String[0]));
    }


}
