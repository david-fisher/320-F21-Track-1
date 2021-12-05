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
        BorderPane leftStack = new BorderPane();
        VBox rightStack = new VBox(10);
        VBox topStack = new VBox();


        // right
        Button saveButton = new Button("Pause");
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

        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction((event -> {
            exitGame.popExit(primaryStage);
        }));

        saveButton.setId("board_side_button");

        exitButton.setId("board_side_button");


        rightStack.getChildren().addAll(saveButton, exitButton);


        // board
        GridPane board = boardGrid.createBoard(players, stackTable);
        inventory Inventory = new inventory();
        Button InventoryButton = Inventory.getInventoryButton(primaryStage);


        // top
        topStack.getChildren().addAll(boardGrid.createTurns());

        // left
        BorderPane.setAlignment(InventoryButton, Pos.CENTER_LEFT);
        leftStack.setBottom(boardGrid.createScore());
        leftStack.setLeft(InventoryButton);

        // main scene
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
