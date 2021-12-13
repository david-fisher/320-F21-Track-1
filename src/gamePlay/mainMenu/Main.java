package gamePlay.mainMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Object a = Main.class.getResource("MainMenuFXML.fxml");
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainMenuFXML.fxml")));
            //loader.setController(new SampleController());
            Scene scene = new Scene(root);
            primaryStage.setTitle("Game Board Editor");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }}