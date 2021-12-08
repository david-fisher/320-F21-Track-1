package loadSave;

import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import mainMenu.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class winGame {

    public static void winScene(Stage primaryStage, String winnerName){
        // TODO: changing how many time you want the color transit her
        int loopNumber = 1000;

        Stage winStage = new Stage();

        Rectangle colorBackground = new Rectangle(500, 300);

        // all colors will occur are below
        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.RED);
        colorList.add(Color.WHITESMOKE);
        colorList.add(Color.GREY);
        colorList.add(Color.WHITE);
        colorList.add(Color.DARKORANGE);    // final color

        ArrayList<FillTransition> transitions = new ArrayList<>();

        for (int i = 0; i < colorList.size() * loopNumber; i++){
            FillTransition ft = new FillTransition(Duration.millis(100), colorBackground);
            int index = i % colorList.size();
            ft.setToValue(colorList.get(index));
            ft.setAutoReverse(true);
            transitions.add(ft);
        }

        /*
        don't change anything in the loop down below
         */
        for (int i = 0; i < transitions.size(); i++){
            int index = i;
            if ((index + 1) < transitions.size()){
                transitions.get(index).setOnFinished(event -> {
                    transitions.get(index + 1).play();
                });
            }
            if (index == 0){
                transitions.get(index).play();
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setId("close_popout");

        closeButton.setOnAction(event -> {
            System.out.println("You closed winning message");
            winStage.close();

            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainMenuFXML.fxml")));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Label greetingMessage1 = new Label("Congratulation!");
        Label greetingMessage2 = new Label(winnerName + " won the game");
        greetingMessage1.setId("greeting_label1");
        greetingMessage2.setId("greeting_label2");

        ImageView trophy = Helpers.Helper.imageMaker("gamePlay/images/trophy.png", 50, 50);
        assert trophy != null;
        trophy.setRotate(-15);

        RotateTransition swing = new RotateTransition();
        swing.setAxis(Rotate.Z_AXIS);
        swing.setByAngle(30);   // swing angle
        swing.setCycleCount(
                (colorList.size() * 100 * loopNumber) / 500
        );
        swing.setDuration(Duration.millis(500));
        swing.setAutoReverse(true);
        swing.setNode(trophy);

        swing.setOnFinished(event -> {
            trophy.setRotate(0);    // rotate back to the original position
        });

        swing.play();

        VBox messageBox = new VBox(5, trophy, greetingMessage1, greetingMessage2);

        VBox topBar = new VBox();
        topBar.getChildren().add(closeButton);
        topBar.setAlignment(Pos.TOP_LEFT);

        messageBox.setAlignment(Pos.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(colorBackground, messageBox, topBar);


        Scene scene = new Scene(stack);
        scene.getStylesheets().add("boardGrid/style.css");

        winStage.setScene(scene);
        winStage.initStyle(StageStyle.TRANSPARENT);
        winStage.initModality(Modality.APPLICATION_MODAL);
        winStage.setResizable(false);
        winStage.show();
    }
}
