import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;

public class Main extends Application {
    public void start(Stage stage){
        Group root = new Group();
        
        // Top tabs ***************************************************************
        Button boardEditor = new Button("Board Editor");
        boardEditor.setPrefSize(100,100);
        
        Button ruleEditor = new Button("Rule Editor");
        ruleEditor.setPrefSize(100, 100);
        ruleEditor.setTranslateX(100);
        
        Button RNG = new Button("RNG");
        RNG.setPrefSize(100, 100);
        RNG.setTranslateX(200);
        
        Button gameOptions = new Button("Game Options");
        gameOptions.setPrefSize(100, 100);
        gameOptions.setTranslateX(300);
        
        Button gameTokens = new Button("Game Tokens");
        gameTokens.setPrefSize(100, 100);
        gameTokens.setTranslateX(400);
        
        TextField gameName = new TextField();
        gameName.setPrefSize(300, 100);
        gameName.setTranslateX(500);
        
        root.getChildren().addAll(boardEditor, ruleEditor, RNG, gameOptions, gameTokens, gameName);
        
        // Side bar *****************************************************************
        
        Scene scene = new Scene(root, 800, 600, Color.rgb(105, 162, 255));
        stage.setTitle("Board Editor");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}