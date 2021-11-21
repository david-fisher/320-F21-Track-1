package loadSave;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class exitGame {

    public static void popExit(Stage primaryStage){

        Stage popStage = new Stage();
        Label confirm = new Label("Confirm Exit");
        Label conFirmMessage = new Label("Are you sure you want to exit?");

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

        popOut.setPrefSize(500, 300);

        popStage.setScene(new Scene(popOut));
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.setResizable(false);
        popStage.show();
    }
}
