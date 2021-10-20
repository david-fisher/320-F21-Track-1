import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Main extends Application {
    public void start(Stage stage){
        Group root = new Group();
        root.getChildren().add(new Rectangle(50, 60, Color.rgb(255, 0, 0)));
        Scene scene = new Scene(root, 800, 600, Color.rgb(255, 255, 255));
        stage.setTitle("Board Editor");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}