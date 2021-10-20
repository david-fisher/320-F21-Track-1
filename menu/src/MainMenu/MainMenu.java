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
import MainMenu.Helpers.*;
import javafx.stage.Stage;

public class MainMenu {
    public static Scene makeScene(Stage primaryStage){
        StackPane root = new StackPane();
        Button playB = Helper.ButtonMaker("Play");
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        playB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(PlaySelectorScene.makeScene(primaryStage, primaryStage.getScene()));
            }
        });
        Button editB = Helper.ButtonMaker("Edit");
        Button exitB = Helper.ButtonMaker("Exit");
        exitB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        GridPane mainMenu = new GridPane();
        mainMenu.add(playB, 0, 0);
        mainMenu.add(editB, 1 ,0);
        mainMenu.setHgap(20);
        mainMenu.setAlignment(Pos.CENTER);
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 20, 100, 20));
        border.setBottom(exitB);

        BorderPane.setAlignment(exitB, Pos.CENTER);

        border.setCenter(mainMenu);
        root.getChildren().add(border);
        //root.setBackground(new Background(backgroundImage));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth()*0.36458333, Screen.getPrimary().getBounds().getHeight()*0.64814814814);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }
}
