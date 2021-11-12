import Helpers.Helper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gamePlayUI {

    public static Scene makeScene(Stage primaryStage, String userName, int height, int width){

        BorderPane mainScene = new BorderPane();
        VBox leftStack = new VBox();
        VBox rightStack = new VBox();

        // right
        Button saveButton = Helper.ButtonMaker("Save", null);
        Button editButton = Helper.ButtonMaker("Edit", null);
        rightStack.getChildren().addAll(saveButton, editButton);

        // board
        GridPane board = boardGrid.createBoard(userName, height, width);

        // left
        leftStack.getChildren().addAll(boardGrid.createScore());

        mainScene.setCenter(board);
        mainScene.setLeft(leftStack);
        mainScene.setRight(rightStack);

        return new Scene(mainScene);
    }
}
