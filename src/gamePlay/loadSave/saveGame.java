package gamePlay.loadSave;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import gamePlay.mainMenu.Main;
import gamePlay.preGame.preGame;
import gamePlay.mainMenu.Helpers.Helper;


import java.io.IOException;
import java.util.Objects;

public class saveGame {
    public static void popSave(Stage primaryStage){

        Stage popStage = new Stage();

        Button homeButton = new Button("Home");
        Button resumeButton = new Button("Resume");
        Button saveButton = new Button("Save");
        resumeButton.setId("resume_button");
        saveButton.setId("save_button");
        homeButton.setId("home_button");
        // after click home button
        homeButton.setOnAction((event -> {
            System.out.println("Welcome back to the home page");

            popStage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainMenuFXML.fxml")));
                Scene scene = new Scene(root);
                primaryStage.setTitle("Game Board Editor");
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //loader.setController(new SampleController());

        }));

        // after click resume button
        resumeButton.setOnAction((event -> {
            popStage.close();
        }));

        saveButton.setOnAction((event -> {
            System.out.println("You have saved a game");

            popStage.close();   // close pop out menu
            primaryStage.setScene(preGame.makeScene(primaryStage)); // back to the new game selection
        }));

        HBox allButton = new HBox(30);
        allButton.getChildren().addAll(resumeButton, homeButton, saveButton);
        allButton.setAlignment(Pos.CENTER);

        VBox alignmentSettler = new VBox(50);
        alignmentSettler.getChildren().addAll(new Label(), allButton);

        StackPane view = new StackPane();
        view.getChildren().add(alignmentSettler);
        view.setAlignment(Pos.CENTER);

        double viewWidth = 550; double viewHeight = 150;
        view.setPrefSize(viewWidth, viewHeight);
        view.setBackground(
                Helper.backgroundColor()
        );
        view.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(view);
        scene.getStylesheets().add("boardGrid/style.css");
        popStage.setScene(scene);
        popStage.initStyle(StageStyle.TRANSPARENT);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.setResizable(false);
        popStage.setX( primaryStage.getX() + (primaryStage.getWidth() / 2) - (viewWidth / 2));
        popStage.setY( primaryStage.getY() + (primaryStage.getHeight() / 2) - (viewHeight / 2));

        popStage.show();
    }

}
