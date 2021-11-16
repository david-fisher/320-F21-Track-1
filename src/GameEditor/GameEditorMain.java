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

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent menu = FXMLLoader.load(getClass().getResource("GameEditorMain.fxml"));
        Parent menu = FXMLLoader.load(getClass().getResource("Views/RuleEditor.fxml"));
        Scene mainMenu = new Scene(menu);
        primaryStage.setScene(mainMenu);
        primaryStage.show();

    }


    public void onClick(ActionEvent event) throws IOException {
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent addMovementPiece = FXMLLoader.load(getClass().getResource("Views/AddMovementPiece.fxml"));
        app_stage.setScene(new Scene (addMovementPiece, 600, 300));
    }

    public void changeScene(Scene in, Scene out){};
}
