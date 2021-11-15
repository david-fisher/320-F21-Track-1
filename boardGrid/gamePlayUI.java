import Helpers.Helper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gamePlayUI {

    public static Scene makeScene(Stage primaryStage, String userName, int height, int width){

        BorderPane mainScene = new BorderPane();
        VBox leftStack = new VBox(50);
        VBox rightStack = new VBox();
        VBox topStack = new VBox();

        // top
        currentTurn CurrentTurn = new currentTurn("Marius");
        topStack.getChildren().addAll(CurrentTurn.getCurrentTurn());

        // right
        Button saveButton = Helper.ButtonMaker("Save", null);
        Button editButton = Helper.ButtonMaker("Edit", null);
        rightStack.getChildren().addAll(saveButton, editButton);

        // board
        GridPane board = boardGrid.createBoard(userName, height, width);
        moveInfo infoBoard = new moveInfo("Move forward 4 spaces and lose next turn or stay in current space");

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
