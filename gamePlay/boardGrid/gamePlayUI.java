package boardGrid;

import Helpers.Helper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import loadSave.exitGame;
import loadSave.saveGame;

import java.util.ArrayList;

public class gamePlayUI {

    public static Scene makeScene(Stage primaryStage, ArrayList<String> players, ArrayList<ArrayList<StackPane>> stackTable) {

        ScrollPane scrollView = new ScrollPane();
        BorderPane mainScene = new BorderPane();
        VBox leftStack = new VBox(150);
        VBox rightStack = new VBox(10);
        VBox topStack = new VBox();


        // right
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            saveGame.popSave(primaryStage);
        });
//        saveButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Stage saveStage = new Stage();
//                Parent root = null;
//                try {
//                    Parent r = FXMLLoader.load(Objects.requireNonNull(boardGrid.class.getResource("pause.fxml")));
//                    Scene scene = new Scene(r);
//                    saveStage.setScene(scene);
//                    saveStage.initModality(Modality.APPLICATION_MODAL);
//                    saveStage.show();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        Button editButton = new Button("Edit");
        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction((event -> {
            exitGame.popExit(primaryStage);
        }));

        saveButton.setId("board_side_button");
        editButton.setId("board_side_button");
        exitButton.setId("board_side_button");


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
        mainScene.setBackground(
                new Background(
                        new BackgroundFill(Helper.backgroundStyle(), null, null)
                )
        );

        scrollView.setFitToHeight(true);
        scrollView.setFitToWidth(true);
        scrollView.setContent(mainScene);

        Scene scene = new Scene(scrollView);
        scene.getStylesheets().add("boardGrid/style.css");

        return scene;
    }
}
