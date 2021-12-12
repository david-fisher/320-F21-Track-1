package gamePlay.preGame;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class preGameLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Pre Game UI");
        stage.setScene(preGame.makeScene(stage));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
