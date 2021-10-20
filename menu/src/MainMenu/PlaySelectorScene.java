package MainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;
import javafx.stage.Stage;
import MainMenu.Helpers.*;

public class PlaySelectorScene {
    protected static Scene makeScene(Stage primaryStage, Scene prev){
        Button newg = Helper.ButtonMaker("New Game");
        newg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(NewGameSelector.makeScene(primaryStage, primaryStage.getScene()));
            }
        });
        Button saveg = Helper.ButtonMaker("Saved Game");
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        saveg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(SavedGameSelector.makeScene(primaryStage, primaryStage.getScene()));
            }
        });
        Button quitg = Helper.ButtonMaker("Back");
        quitg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        VBox pVbox = new VBox(20);
        pVbox.setAlignment(Pos.CENTER);
        pVbox.getChildren().addAll(newg, saveg, quitg);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(pVbox);
        //stackPane.setBackground(new Background(backgroundImage));
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(stackPane, Screen.getPrimary().getBounds().getWidth()*0.36458333, Screen.getPrimary().getBounds().getHeight()*0.64814814814);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }
}
