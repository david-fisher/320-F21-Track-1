import Helpers.Helper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class gamePlayUI {

    public static Scene makeScene(Stage primaryStage, ArrayList<String> players, ArrayList<ArrayList<StackPane>> stackTable) {

        BorderPane mainScene = new BorderPane();
        VBox leftStack = new VBox(150);
        VBox rightStack = new VBox();
        VBox topStack = new VBox();


        // right
        Button saveButton = Helper.ButtonMaker("Save", null);
        Button editButton = Helper.ButtonMaker("Edit", null);
        rightStack.getChildren().addAll(saveButton, editButton);

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
