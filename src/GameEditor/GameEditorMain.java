package GameEditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEditorMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    
    /*
      main Stage for the GameEditor.
      Integrates the boardEditor into the GameEditor menu.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Views/GameEditorMain.fxml"));
        Scene mainMenu = new Scene(menu);
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
}
