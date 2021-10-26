package MainMenu;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import MainMenu.Helpers.*;
import javafx.util.Duration;

public class NewGameSelector {
    protected static Scene makeScene(Stage primaryStage, Scene prev) {
        BorderPane border = new BorderPane();
        border.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        ScrollPane scroll = new ScrollPane();
        scroll.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");

        StackPane stack = new StackPane();
        stack.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox vb = new VBox(20);
        vb.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Text gameT = new Text("Game");
        gameT.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));

        border.setTop(gameT);
        border.setAlignment(gameT, Pos.CENTER);

        vb.setAlignment(Pos.CENTER);
        Helper.Games[] games = Helper.makeGames();
        List<Button> buttonList = new ArrayList<>();
        for (Helper.Games g : games) {
            buttonList.add(Helper.ButtonMaker(g.name));
        }
        Button eBut = Helper.ButtonMaker("Back");
        eBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        border.setBottom(eBut);
        border.setAlignment(eBut, Pos.CENTER);

        buttonList.get(buttonList.size() - 1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(TutorialScene.tutorialScene(primaryStage, primaryStage.getScene()));
            }
        });
        for (Button b : buttonList) {
            b.setPrefWidth(500);
            b.setWrapText(true);
            vb.getChildren().add(b);
        }
        stack.getChildren().add(vb);
        scroll.setContent(stack);
        scroll.setFitToWidth(true);
        border.setCenter(scroll);
        //BackgroundImage backgroundImage = new BackgroundImage(new Image("tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //border.setBackground(new Background(backgroundImage));
        //vb.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(border, Screen.getPrimary().getBounds().getWidth() * 0.36458333, Screen.getPrimary().getBounds().getHeight() * 0.64814814814);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }
}
