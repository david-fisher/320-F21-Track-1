import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class Menu extends Application {
    public static void main(String[] args) {launch(args); }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Board Game Editor Student Edition 2017");
        primaryStage.setResizable(false);
        StackPane root = new StackPane();
        Button playB = ButtonMaker("Play");
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        playB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(playSceneSelector(primaryStage, primaryStage.getScene()));
            }
        });
        Button editB = ButtonMaker("Edit");
        Button exitB = ButtonMaker("Exit");
        exitB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        GridPane mainMenu = new GridPane();
        mainMenu.add(playB, 0, 0);
        mainMenu.add(editB, 1 ,0);
        mainMenu.setHgap(20);
        mainMenu.setAlignment(Pos.CENTER);
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 20, 100, 20));
        border.setBottom(exitB);

        BorderPane.setAlignment(exitB, Pos.CENTER);

        border.setCenter(mainMenu);
        root.getChildren().add(border);
        //root.setBackground(new Background(backgroundImage));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth()*0.36458333, 700);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
    private Button ButtonMaker(String text){
        Button btn = new Button();
        btn.setText(text);
        btn.setPrefWidth(300);
        //btn.setPrefHeight(100);
        btn.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));
        return(btn);
    }

    private Scene playSceneSelector(Stage primaryStage, Scene prev){
        Button newg = ButtonMaker("New Game");
        newg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(levelSelector(primaryStage, primaryStage.getScene()));
            }
        });
        Button saveg = ButtonMaker("Saved Game");
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        saveg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(savedGame(primaryStage, primaryStage.getScene()));
            }
        });
        Button quitg = ButtonMaker("Back");
        quitg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        VBox pVbox = new VBox(20);
        pVbox.setAlignment(Pos.CENTER);
        pVbox.getChildren().addAll(newg, saveg, quitg);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(pVbox);
        //stackPane.setBackground(new Background(backgroundImage));
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(stackPane, Screen.getPrimary().getBounds().getWidth()*0.36458333, 700);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }
    public class Games {
        String name;
        String date;

        Games(String n, String d) {
            this.name = n;
            this.date = d;
        }
    }

    public Games[] makeGames() {

        Games game1 = new Games("Life", "1:23:45");
        Games game2 = new Games("Life 2", "1:23:45");
        Games game3 = new Games("250 With Marius", "1:23:45");
        Games game4 = new Games("Standing up and saying your name to the rest of the class", "1:23:45");
        Games game5 = new Games("Track 1: Total War", "1:23:45");
        Games game6 = new Games("Finally Setting Up Github", "1:23:45");
        Games game7 = new Games("Forgetting the Microphone", "1:23:45");
        Games game8 = new Games("Extreme Monopoly", "1:23:45");
        Games[] games = new Games[]{game1, game2, game3, game4, game5, game6, game7, game8};
        return games;
    }

    private Scene savedGame(Stage primaryStage, Scene prev){
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(20);
        HBox hBox = new HBox(20);
        Text gameT = new Text("   Game                                               ");
        Text gameD = new Text("Play Time");
        gameT.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        gameD.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        hBox.getChildren().addAll(gameT,gameD);
        borderPane.setTop(hBox);

        ScrollPane scroll = new ScrollPane();
        Games[] games = makeGames();
        List<Button> bList = new ArrayList<>();
        for(Games g : games){
            bList.add(ButtonMaker(g.name));
            vBox.getChildren().add(dateHbox(bList.get(bList.size()-1), textMaker(g.date)));
        }
        Button eBut = ButtonMaker("Back");
        eBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        borderPane.setBottom(eBut);
        borderPane.setAlignment(eBut, Pos.CENTER);
        for(Button b : bList){
            b.setPrefWidth(500);
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
        Scene scene = new Scene(borderPane, Screen.getPrimary().getBounds().getWidth()*0.36458333, 700);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }

    private HBox dateHbox(Button b, Text t){
        HBox h = new HBox(20);
        h.getChildren().setAll(b, t);
        return h;
    }

    private Text textMaker(String s){
        Text t = new Text(s);
        t.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        return t;
    }

    private Scene levelSelector(Stage primaryStage, Scene prev){
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
        Games[] games = makeGames();
        List<Button> buttonList = new ArrayList<>();
        for(Games g : games) {
            buttonList.add(ButtonMaker(g.name));
        }
        Button eBut = ButtonMaker("Back");
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
                primaryStage.setScene(tutorialScene(primaryStage, primaryStage.getScene()));
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
        Scene scene = new Scene(border, Screen.getPrimary().getBounds().getWidth()*0.36458333, 700);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }

    private  Scene tutorialScene(Stage primaryStage, Scene prev){
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox(20);
        Button pva = ButtonMaker("Player vs. AI");
        Button pvp = ButtonMaker("Player vs. Player");
        Button back = ButtonMaker("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(prev);
            }
        });
        pva.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage newStage = new Stage();
                newStage.setScene(monopolyScene(newStage));
                newStage.show();
                primaryStage.close();
            }
        });
        RadioButton radio = new RadioButton("With Tutorial Mode");
        radio.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(pva, pvp, radio, back);
        stackPane.getChildren().add(vBox);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("MainMenu/tile.jpg", 200, 200, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //stackPane.setBackground(new Background(backgroundImage));
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        //vBox.setBackground(new Background(backgroundImage));
        vBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(stackPane, Screen.getPrimary().getBounds().getWidth()*0.36458333, 700);
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));;
        return scene;
    }

    private Scene monopolyScene(Stage stage){
        stage.setMaximized(true);
        stage.setTitle("Extreme Monopoly");
        Image img = new Image("https://i.pinimg.com/736x/4d/7c/15/4d7c15447aa27222caa7d490c3c7b816.jpg");
        ImageView imageView = new ImageView(img);
        imageView.setPreserveRatio(true);
        //imageView.fitWidthProperty().bind(stage.widthProperty());
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        System.out.println("Here3");
        Scene scene = new Scene(stackPane);
        stackPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#81c483")), new Stop(1, Color.WHITE)));
        return scene;
    }

    private Scene eScene(Stage primaryStage){
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


    private Scene rScene(Stage primaryStage){
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