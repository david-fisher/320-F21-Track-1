import javafx.application.Application;
import javafx.stage.Stage;
import MainMenu.*;
import javafx.scene.Scene;

public class MenuLauncher extends Application {
    public static void main(String[] args) {launch(args); }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Board Game Editor Student Edition 2017");
        primaryStage.setResizable(false);
        Scene scene = MainMenu.makeScene(primaryStage);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
