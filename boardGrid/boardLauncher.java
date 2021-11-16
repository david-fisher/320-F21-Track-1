import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class boardLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        int[] size = {8, 7};

        ArrayList<String> playerList = new ArrayList<>();
        playerList.add(null);
        playerList.add("Fisher");
        playerList.add("Marius");

        Scene scene = gamePlayUI.makeScene(stage, playerList, size[0], size[1]);
        stage.setTitle("Game Board");


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}