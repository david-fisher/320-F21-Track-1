package loadSave;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class exitGame {

    public static void popExit(Stage primaryStage){

        Stage popStage = new Stage();
        Label confirm = new Label("Confirm Exit");
        Label conFirmMessage = new Label("Are you sure you want to exit?");
        confirm.setId("exit_text");
        conFirmMessage.setId("exit_text");

        Button exitButton = new Button("Exit");
        Button cancelButton = new Button("Cancel");

        // after user clicks exit button
        exitButton.setOnAction((event -> {
            Platform.exit();
        }));

        // after user clicks cancel button
        cancelButton.setOnAction((event -> {
            popStage.close();
        }));

        exitButton.setId("resume_button");
        cancelButton.setId("save_button");

        HBox allButton = new HBox(30);
        allButton.getChildren().addAll(exitButton, cancelButton);
        allButton.setAlignment(Pos.BOTTOM_CENTER);

        VBox confirmBox = new VBox(30);
        confirm.setAlignment(Pos.CENTER);
        conFirmMessage.setAlignment(Pos.CENTER);
        confirmBox.getChildren().addAll(confirm, conFirmMessage);

        VBox popOut = new VBox(30);
        popOut.getChildren().addAll(confirmBox, allButton);
        popOut.setAlignment(Pos.CENTER);

        double popOutWidth = 500; double popOutHeight = 300;
        popOut.setPrefSize(popOutWidth, popOutHeight);

        popOut.setBackground(
                Helpers.Helper.backgroundColor()
        );
        popOut.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(popOut);
        scene.getStylesheets().add("boardGrid/style.css");

        popStage.setScene(scene);
        popStage.initStyle(StageStyle.TRANSPARENT);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.setResizable(false);
        double a = popOut.getWidth();
        popStage.setX( primaryStage.getX() + (primaryStage.getWidth() / 2) - (popOutWidth / 2));
        popStage.setY( primaryStage.getY() + (primaryStage.getHeight() / 2) - (popOutHeight / 2));
        popStage.show();
    }
}