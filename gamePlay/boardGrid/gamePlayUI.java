import Helpers.Helper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class gamePlayUI {

    public static Scene makeScene(Stage primaryStage, ArrayList<String> players, ArrayList<ArrayList<StackPane>> stackTable) {

        BorderPane mainScene = new BorderPane();
        VBox leftStack = new VBox(150);
        VBox rightStack = new VBox();
        VBox topStack = new VBox();


        // right
        Button saveButton = Helper.ButtonMaker("Save", null);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage saveStage = new Stage();
                Parent root = null;
                try {
                    Parent r = FXMLLoader.load(getClass().getClassLoader().getResource("pause.fxml"));
                    Scene scene = new Scene(r);
                    saveStage.setScene(scene);
                    saveStage.initModality(Modality.APPLICATION_MODAL);
                    saveStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button editButton = Helper.ButtonMaker("Edit", null);
        
        Button exitButton = Helper.ButtonMaker("Exit", null);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });


        rightStack.getChildren().addAll(saveButton, editButton, exitButton);


        // board
        GridPane board = boardGrid.createBoard(players, stackTable);
        moveInfo infoBoard = new moveInfo("Move forward 4 spaces and lose next turn or stay in current space");


        // top
        topStack.getChildren().addAll(boardGrid.createTurns());

        // left
        leftStack.getChildren().addAll(infoBoard.getMoveInfo(), boardGrid.createScore());
        leftStack.setAlignment(Pos.BOTTOM_CENTER);

        mainScene.setCenter(board);
        mainScene.setLeft(leftStack);
        mainScene.setRight(rightStack);
        mainScene.setTop(topStack);

        return new Scene(mainScene);
    }
}
