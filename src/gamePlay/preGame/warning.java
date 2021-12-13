package gamePlay.preGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import gamePlay.mainMenu.Main;

public class warning {

    public static void warningPop(String text){
        Stage popStage = new Stage();

        Label warningMessage = new Label(text);
        Button resumeButton = new Button("Resume");
        warningMessage.setId("exit_text");
        resumeButton.setId("resume_button");

        // after click resume button
        resumeButton.setOnAction((event -> {
            popStage.close();
        }));

        VBox view = new VBox(30);
        view.getChildren().addAll(warningMessage, resumeButton);
        view.setAlignment(Pos.CENTER);
        view.setBackground(Main.saveBackground());

        view.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(view);
        scene.getStylesheets().add("boardGrid/style.css");
        popStage.setScene(scene);

        popStage.initStyle(StageStyle.TRANSPARENT);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.setResizable(false);

        popStage.show();
    }
}
