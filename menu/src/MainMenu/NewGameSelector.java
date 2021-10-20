package MainMenu;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
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

import MainMenu.Helpers.*;
import javafx.util.Duration;

public class NewGameSelector {
    protected static Scene makeScene(Stage primaryStage, Scene prev){
        BorderPane border = new BorderPane();
        border.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        ScrollPane scroll = new ScrollPane();
        scroll.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");

        StackPane stack = new StackPane();
        stack.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox vb = new VBox(20);
        vb.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Text gameT = new Text("Game");
        gameT.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));

        border.setTop(gameT);
        border.setAlignment(gameT, Pos.CENTER);

        vb.setAlignment(Pos.CENTER);
        Helper.Games[] games = Helper.makeGames();
        List<Button> buttonList = new ArrayList<>();
        for(Helper.Games g : games) {
            buttonList.add(Helper.ButtonMaker(g.name));
        }
        Button eBut = Helper.ButtonMaker("Back");
        eBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        border.setBottom(eBut);
        border.setAlignment(eBut, Pos.CENTER);
        buttonList.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage newStage = new Stage();
                newStage.setScene(eScene(newStage));
                newStage.show();
                primaryStage.close();;
            }
        });
        buttonList.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Stage newStage = new Stage();
                newStage.setScene(rScene(newStage));
                newStage.show();
                primaryStage.close();

            }
        });
        buttonList.get(buttonList.size()-1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(TutorialScene.tutorialScene(primaryStage, primaryStage.getScene()));
            }
        });
        for(Button b: buttonList) {
            b.setPrefWidth(500);
            b.setWrapText(true);
            vb.getChildren().add(b);
        }
        stack.getChildren().add(vb);
        scroll.setContent(stack);
        scroll.setFitToWidth(true);
        border.setCenter(scroll);
        //BackgroundImage backgroundImage = new BackgroundImage(new Image("tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //border.setBackground(new Background(backgroundImage));
        //vb.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(border, Screen.getPrimary().getBounds().getWidth()*0.36458333, Screen.getPrimary().getBounds().getHeight()*0.64814814814);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }

    private static Scene eScene(Stage primaryStage){
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        StackPane rootPane = new StackPane();
        AnchorPane anchorPane = new AnchorPane();
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        Button btn = new Button();

        btn.setPrefWidth(100);
        btn.setPrefHeight(100);
        Button eBtn = new Button();
        eBtn.setText("Thanks for Playing!");
        eBtn.setOpacity(0);
        eBtn.setFont(Font.font("System", 30));
        Label qLabel = new Label();
        qLabel.setText("Click the button for the meaning of life");
        qLabel.setFont(Font.font("System", 30));
        Label aLabel = new Label();
        aLabel.setText("Burritos :D");
        aLabel.setOpacity(0);
        Image img = new Image("https://gourmandelle.com/wp-content/uploads/2018/09/vegan-burritos-recipe-burrito-vegetarian-720x405.jpg");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setOpacity(0);
        FadeTransition ft = new FadeTransition(Duration.millis(5000), imgView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        FadeTransition ftt = new FadeTransition(Duration.millis(5000), aLabel);
        ftt.setFromValue(0);
        ftt.setToValue(1);
        ftt.setCycleCount(1);
        FadeTransition ftq = new FadeTransition(Duration.millis(5000), qLabel);
        ftq.setFromValue(1);
        ftq.setToValue(0);
        ftq.setCycleCount(1);
        FadeTransition ftb = new FadeTransition(Duration.millis(5000), btn);
        ftb.setFromValue(1);
        ftb.setToValue(0);
        ftb.setCycleCount(1);
        FadeTransition fte = new FadeTransition(Duration.millis(5000), eBtn);
        fte.setFromValue(0);
        fte.setToValue(1);
        fte.setCycleCount(1);
        ParallelTransition p1 = new ParallelTransition(ftq, ftb);
        ParallelTransition p2 = new ParallelTransition(ft, ftt);
        SequentialTransition s = new SequentialTransition(p1, p2, fte);
        final boolean[] picked = {true};
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(picked[0]) {
                    aLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 40));
                    s.play();
                    picked[0] = false;
                    grid2.toFront();
                }
            }
        });
        eBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(picked[0] == false) {
                    primaryStage.close();
                }
            }
        });


        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(qLabel, 0, 0, 1, 1);
        grid.add(btn, 0, 1, 1, 1);
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        GridPane.setHalignment(btn, HPos.CENTER);
        GridPane.setValignment(btn, VPos.CENTER);
        grid2.add(aLabel,0, 0, 1, 1);
        grid2.add(imgView, 0, 1, 1, 1);
        grid2.add(eBtn, 0, 2, 1, 1);
        GridPane.setHalignment(aLabel, HPos.CENTER);
        GridPane.setValignment(aLabel, VPos.CENTER);
        GridPane.setValignment(qLabel, VPos.CENTER);
        GridPane.setHalignment(qLabel, HPos.CENTER);
        GridPane.setValignment(imgView, VPos.CENTER);
        GridPane.setHalignment(imgView, HPos.CENTER);
        GridPane.setValignment(eBtn, VPos.CENTER);
        GridPane.setHalignment(eBtn, HPos.CENTER);
        rootPane.getChildren().addAll(grid2, grid);
        Scene scene = new Scene(rootPane);
        return scene;
    }


    private static Scene rScene(Stage primaryStage){
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(Color.color(0.2, 0.2, 0.2), CornerRadii.EMPTY, Insets.EMPTY)));
        StackPane rootPane = new StackPane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.color(0.2, 0.2, 0.2), CornerRadii.EMPTY, Insets.EMPTY)));
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        Button btn = new Button();
        btn.setPrefWidth(100);
        btn.setPrefHeight(100);
        btn.setBackground(new Background(new BackgroundFill(Color.color(0.5, 0.5, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        Button eBtn = new Button();
        eBtn.setText("You will be forgotten");
        eBtn.setOpacity(0);
        eBtn.setFont(Font.font("System", 30));
        eBtn.setBackground(new Background(new BackgroundFill(Color.color(0.5, 0.5, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        eBtn.setTextFill(Color.WHITESMOKE);
        Stop[] stop = {new Stop(0, Color.RED),
                new Stop(0.5, Color.GREEN),
                new Stop(1, Color.BLUE)};
        Label qLabel = new Label();
        qLabel.setText("Click the button for the meaning of life");
        qLabel.setFont(Font.font("System", 30));
        qLabel.setTextFill(Color.WHITESMOKE);
        Label aLabel = new Label();
        aLabel.setText("Work until you die");
        aLabel.setOpacity(0);
        aLabel.setTextFill(Color.WHITESMOKE);
        Image img = new Image("https://thumbs.dreamstime.com/z/freshly-dug-grave-cemetery-flowers-top-ground-beautiful-floral-bouquet-laid-dirt-covering-casket-142467473.jpg");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(650);
        imgView.setOpacity(0);
        FadeTransition ft = new FadeTransition(Duration.millis(5000), imgView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        FadeTransition ftt = new FadeTransition(Duration.millis(5000), aLabel);
        ftt.setFromValue(0);
        ftt.setToValue(1);
        ftt.setCycleCount(1);
        FadeTransition ftq = new FadeTransition(Duration.millis(5000), qLabel);
        ftq.setFromValue(1);
        ftq.setToValue(0);
        ftq.setCycleCount(1);
        FadeTransition ftb = new FadeTransition(Duration.millis(5000), btn);
        ftb.setFromValue(1);
        ftb.setToValue(0);
        ftb.setCycleCount(1);
        FadeTransition fte = new FadeTransition(Duration.millis(5000), eBtn);
        fte.setFromValue(0);
        fte.setToValue(1);
        fte.setCycleCount(1);
        ParallelTransition p1 = new ParallelTransition(ftq, ftb);
        ParallelTransition p2 = new ParallelTransition(ft, ftt);
        SequentialTransition s = new SequentialTransition(p1, p2, fte);
        final boolean[] picked = {true};
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(picked[0]) {
                    aLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 40));
                    s.play();
                    picked[0] = false;
                    grid2.toFront();
                }
            }
        });
        eBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(picked[0] == false) {
                    primaryStage.close();
                }
            }
        });


        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(qLabel, 0, 0, 1, 1);
        grid.add(btn, 0, 1, 1, 1);
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        GridPane.setHalignment(btn, HPos.CENTER);
        GridPane.setValignment(btn, VPos.CENTER);
        grid2.add(aLabel,0, 0, 1, 1);
        grid2.add(imgView, 0, 1, 1, 1);
        grid2.add(eBtn, 0, 2, 1, 1);
        GridPane.setHalignment(aLabel, HPos.CENTER);
        GridPane.setValignment(aLabel, VPos.CENTER);
        GridPane.setValignment(qLabel, VPos.CENTER);
        GridPane.setHalignment(qLabel, HPos.CENTER);
        GridPane.setValignment(imgView, VPos.CENTER);
        GridPane.setHalignment(imgView, HPos.CENTER);
        GridPane.setValignment(eBtn, VPos.CENTER);
        GridPane.setHalignment(eBtn, HPos.CENTER);
        rootPane.getChildren().addAll(grid2, grid);
        Scene scene = new Scene(rootPane);
        scene.setFill(Color.BLACK);
        return scene;
    }
}
