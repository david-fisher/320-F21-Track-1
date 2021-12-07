package loadSave;

import javafx.animation.FillTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class winPopout {

    public static void winScene(Stage primaryStage, String winnerName){
        Stage winStage = new Stage();

        Rectangle colorBackground = new Rectangle(500, 300);

        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.RED);
        colorList.add(Color.WHITESMOKE);
        colorList.add(Color.GREY);
        colorList.add(Color.WHITE);

        ArrayList<FillTransition> transitions = new ArrayList<>();
        for (Color c : colorList){
            FillTransition ft = new FillTransition(Duration.millis(100), colorBackground);
            ft.setToValue(c);
            ft.setAutoReverse(true);
            transitions.add(ft);
        }

        for (int i = 0; i < transitions.size(); i++){
            if ((i + 1) < transitions.size()){
                int tempIndex = i;
                transitions.get(i).setOnFinished(event -> {
                    transitions.get(tempIndex + 1).play();
                });
            }
            if (i == 0){
                transitions.get(i).play();
            }
        }



        StackPane stack = new StackPane();
        stack.getChildren().add(colorBackground);


        Scene scene = new Scene(stack);

        winStage.setScene(scene);
        winStage.initStyle(StageStyle.TRANSPARENT);
        winStage.initModality(Modality.APPLICATION_MODAL);
        winStage.setResizable(false);
        winStage.show();
    }
}
