package boardGrid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class boardLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        int[] size = {8, 7};
        ArrayList<ArrayList<StackPane>> stackTable = new ArrayList<>();
        for (int i = 0; i < size[0]; i++){
            ArrayList<StackPane> row = new ArrayList<>();
            for (int j = 0; j < size[1]; j++){
                row.add(null);
            }

            stackTable.add(row);
        }

        ArrayList<String> playerList = new ArrayList<>();
        playerList.add(null);
        playerList.add("Fisher");
        playerList.add("Marius");

        Scene scene = gamePlayUI.makeScene(stage, playerList, stackTable);
        stage.setTitle("Game Board");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}