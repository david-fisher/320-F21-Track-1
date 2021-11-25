package loadSave;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class saveGame {
    public static void popSave(Stage primaryStage){

        Stage popStage = new Stage();

        Button resumeButton = new Button("Resume");
        Button saveButton = new Button("Save");
        resumeButton.setId("resume_button");
        saveButton.setId("save_button");
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
        allButton.setAlignment(Pos.CENTER);

        VBox alignmentSettler = new VBox(50);
        alignmentSettler.getChildren().addAll(new Label(), allButton);

        StackPane view = new StackPane();
        view.getChildren().add(alignmentSettler);
        view.setAlignment(Pos.CENTER);
        view.setPrefSize(500, 150);
        view.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(33, 37, 43), null, null
                        )
                )
        );

        Scene scene = new Scene(view);
        scene.getStylesheets().add("boardGrid/style.css");
        popStage.setScene(scene);
        popStage.initStyle(StageStyle.TRANSPARENT);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.setResizable(false);

        popStage.show();
    }

}
