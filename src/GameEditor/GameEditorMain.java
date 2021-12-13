package GameEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
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
        TabPane tabpane = (TabPane) menu.getChildrenUnmodifiable().get(0);
        tabpane.getTabs().get(0).setContent(new BoardEditor().startBoardEditor(primaryStage));
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
}
