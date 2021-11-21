package loadSave;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class saveGame {
    public static void popSave(Stage primaryStage){

        Stage popStage = new Stage();

        Button resumeButton = new Button("Resume");
        Button saveButton = new Button("Save");
        resumeButton.setId("main-menu-button");
        saveButton.setId("main-menu-button");
        // after click resume button
        resumeButton.setOnAction((event -> {
            popStage.close();
        }));

        saveButton.setOnAction((event -> {
            System.out.println("You have saved a game");

            popStage.close();   // close pop out menu
            primaryStage.setScene(preGame.preGame.makeScene(primaryStage)); // back to the new game selection
        }));

        HBox allButton = new HBox(30);
        allButton.getChildren().addAll(resumeButton, saveButton);
        allButton.setAlignment(Pos.BOTTOM_CENTER);

        StackPane view = new StackPane();
        view.getChildren().add(allButton);
        view.setPrefSize(500, 300);

        Scene scene = new Scene(view);
        scene.getStylesheets().add("boardGrid/style.css");
        popStage.setScene(scene);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.setResizable(false);

        popStage.show();
    }

}
