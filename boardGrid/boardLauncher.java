import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class boardLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        int[] size = {8, 7};

        Scene scene = gamePlayUI.makeScene(stage, null,  size[0], size[1]);
        stage.setTitle("Game Board");

//        cell c = new cell(
//                "Hey fisher", null);
//        boardGrid.updateCell(c, 1, 1);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}