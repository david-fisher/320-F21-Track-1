package MainMenu;

import MainMenu.Helpers.Helper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SavedGameSelector {
    protected static Scene makeScene(Stage primaryStage, Scene prev){
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(20);
        HBox hBox = new HBox(20);
        Text gameT = new Text("   Game                                   ");
        Text gameD = new Text("Play Time");
        gameT.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        gameD.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        hBox.getChildren().addAll(gameT,gameD);
        borderPane.setTop(hBox);

        ScrollPane scroll = new ScrollPane();
        Helper.Games[] games = Helper.makeGames();
        List<Button> bList = new ArrayList<>();
        for(Helper.Games g : games){
            bList.add(Helper.ButtonMaker(g.name));
            vBox.getChildren().add(Helper.dateHbox(bList.get(bList.size()-1), Helper.textMaker(g.date)));
        }
        Button eBut = Helper.ButtonMaker("Back");
        eBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        borderPane.setBottom(eBut);
        borderPane.setAlignment(eBut, Pos.CENTER);
        for(Button b : bList){
            b.setPrefWidth(Screen.getPrimary().getBounds().getHeight()*0.64814814814*0.71428571428);
            b.setWrapText(true);
        }
        scroll.setContent(vBox);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        borderPane.setCenter(scroll);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //vBox.setBackground(new Background(backgroundImage));
        vBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        //borderPane.setBackground(new Background(backgroundImage));
        borderPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(borderPane, Screen.getPrimary().getBounds().getWidth()*0.36458333, Screen.getPrimary().getBounds().getHeight()*0.64814814814);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }
}
