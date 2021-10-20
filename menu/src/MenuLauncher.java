import javafx.application.Application;
import javafx.stage.Stage;
import MainMenu.*;

public class MenuLauncher extends Application {
    public static void main(String[] args) {launch(args); }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Board Game Editor Student Edition 2017");
        primaryStage.setResizable(false);
        primaryStage.setScene(MainMenu.makeScene(primaryStage));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
