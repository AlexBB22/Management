package nl.tudelft.oopp;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.views.MainView;



public class MainApp {
    public static void main(String[] args) {
        MainView.main((new String[0]));
    }

    /**.
     * Function that can switch scene
     * @param actionEvent the event that happens when the Scene switches
     * @param source the source of the event
     * @throws IOException throws an exception if there is something wrong with the input or output
     */
    public static void switchScene(Event actionEvent, String source, String... title)
                                    throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(source));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));

        if (title != null) {
            stage.setTitle(title[0]);
        }
    }

}
