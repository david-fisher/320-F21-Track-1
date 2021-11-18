package MainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import MainMenu.Helpers.*;

public class TutorialScene {
    protected static Scene tutorialScene(Stage primaryStage, Scene prev){
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox(20);
        Button pva = Helper.ButtonMaker("Player vs. AI");
        Button pvp = Helper.ButtonMaker("Player vs. Player");
        Button back = Helper.ButtonMaker("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        pva.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage newStage = new Stage();
                newStage.setScene(monopolyScene(newStage));
                newStage.show();
                primaryStage.close();
            }
        });
        RadioButton radio = new RadioButton("With Tutorial Mode");
        radio.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(pva, pvp, radio, back);
        stackPane.getChildren().add(vBox);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //stackPane.setBackground(new Background(backgroundImage));
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        //vBox.setBackground(new Background(backgroundImage));
        vBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(stackPane, Screen.getPrimary().getBounds().getWidth()*0.36458333, Screen.getPrimary().getBounds().getHeight()*0.64814814814);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));;
        return scene;
    }

    private static Scene monopolyScene(Stage stage){
        stage.setMaximized(true);
        stage.setTitle("Extreme Monopoly");
        Image img = new Image("https://i.pinimg.com/736x/4d/7c/15/4d7c15447aa27222caa7d490c3c7b816.jpg");
        ImageView imageView = new ImageView(img);
        imageView.setPreserveRatio(true);
        //imageView.fitWidthProperty().bind(stage.widthProperty());
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        System.out.println("Here3");
        Scene scene = new Scene(stackPane);
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }
}
