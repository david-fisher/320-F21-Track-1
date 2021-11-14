package GameEditor.TokenUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MovementPiece extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent addMovementPiece = FXMLLoader.load(getClass().getResource("AddMovementPiece.fxml"));
        Parent movementPiecesMainPage = FXMLLoader.load(getClass().getResource("MovementPiece.fxml"));


        Scene addPiece = new Scene(addMovementPiece);
        Scene mainMovementPiecePage = new Scene(movementPiecesMainPage);

        primaryStage.setTitle("Movement Piece");
        primaryStage.setWidth(600);
        primaryStage.setHeight(300);
        primaryStage.setScene(mainMovementPiecePage);


        primaryStage.show();
    }

    public void addMovementPiece(ActionEvent event) throws IOException {
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent addMovementPiece = FXMLLoader.load(getClass().getResource("AddMovementPiece.fxml"));
        app_stage.setScene(new Scene (addMovementPiece));

    }

    public static void main(String[] args) {
        launch(args);
    }

}

/*
- JavaFX themes
- make it so scene resizes with stage
-



 */






//primary Stage would probably Token Editor
//scenes would be different tokens

//primary stage and other stages should have a global width and height

//create style sheet