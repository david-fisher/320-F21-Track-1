package boardGrid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class boardLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        int[] size = {6, 6};

        Scene scene = boardGrid.makeScene(stage, size[0], size[1]);
        stage.setTitle("save game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}