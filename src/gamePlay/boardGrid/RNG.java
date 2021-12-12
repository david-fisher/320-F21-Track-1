package gamePlay.boardGrid;

import gamePlay.mainMenu.Helpers.Helper;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Double.min;


public class RNG {
    Button dButton;
    Button sButton;
    Button deckButton;
    Label dText;
    Label sText;
    Label deckText;
    ArrayList<Integer> curDeck;
    ArrayList<Integer> drawnDeck;
    private boolean mutux = false;  // a c++ like lock

    public RNG(int diceMin, int diceMax, String[] colors, ArrayList<Integer> deck){
        this.dText = labelMaker();
        this.dButton = makeDie(diceMin, diceMax, this.dText);
        this.sText = labelMaker();
        this.sButton = makeSpinner(colors, sText);
        this.curDeck = (ArrayList<Integer>) deck.clone();
        this.drawnDeck = new ArrayList<Integer>();
        this.deckText = labelMaker();
        this.deckButton = makeDeck(deckText);
    }

    private void afterHover(Node node, String standardStyle, String hoverStyle){
        node.styleProperty()
                .bind(
                        Bindings.when(node.hoverProperty())
                                .then(
                                        new SimpleStringProperty(hoverStyle)
                                )
                                .otherwise(
                                        new SimpleStringProperty(standardStyle)
                                )
        );
    }

    private Button makeDie(int diceMin, int diceMax, Label value){
        Button dice = new Button();
        ImageView graph = Helper.imageMaker("src/gamePlay/images/Dice.png", 100, 100);
        dice.setGraphic(graph);



        dice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // write a lock, make sure only one animation at a time
                if (mutux) { return; }
                mutux = true;
                // rotate 360
                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Z_AXIS);
                rotate.setByAngle(360);
                rotate.setCycleCount(2);
                rotate.setDuration(Duration.millis(500));
                rotate.setAutoReverse(true);

                rotate.setNode(graph);

                // after the animation is done, do:
                rotate.setOnFinished(event1 -> {

                    RotateTransition rotate2 = new RotateTransition();
                    rotate2.setAxis(Rotate.Z_AXIS);
                    rotate2.setByAngle(360);
                    rotate2.setCycleCount(2);
                    rotate2.setDuration(Duration.millis(800));
                    rotate2.setAutoReverse(true);

                    rotate2.setNode(graph);

                    rotate2.setOnFinished(event2 -> {
                        value.setOpacity(0);
                        int Num = (int) ((Math.random() * (diceMax-diceMin)) + diceMin);
                        value.setText(String.valueOf(Num));
                        FadeTransition ft = new FadeTransition(Duration.millis(800), value);
                        ft.setFromValue(0);
                        ft.setToValue(1);
                        ft.play();
                        mutux = false;
                    });

                    rotate2.play();
                });

                rotate.play();

            }
        });
        dice.setId("board_side_button");
        return dice;
    }

    private Label labelMaker(){
        Label label = new Label("--");
        label.setFont(Font.font("Helvetica", 25));
//        label.setStyle("-fx-background-color: #787f8c; "
//                    "-fx-text-alignment: center;" +
//                    "-fx-max-width: 50;" +
//                        "-fx-min-width: 50"
//        );
        return label;
    }

    public int getDiceVal(){
        return Integer.parseInt(dText.getText());
    }
    public void setDiceText(String s){
        dText.setText(s);
    }

    private Button makeSpinner(String[] colors, Label text){
        Random rand = new Random();
        Button spinner = new Button();
        ImageView graph = Helper.imageMaker("src/gamePlay/images/Spinner.png", 100, 100);
        spinner.setGraphic(graph);
        spinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // write a lock, make sure only one animation at a time
                if (mutux) { return; }
                mutux = true;

                // rotate 360 + 360 / 8
                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Z_AXIS);
                rotate.setByAngle(405);
                rotate.setCycleCount(1);
                rotate.setDuration(Duration.millis(1000));
                rotate.setAutoReverse(true);

                rotate.setNode(graph);

                // do after the animation is done
                rotate.setOnFinished(event1 -> {

                    int randIndex = rand.nextInt(colors.length);
                    text.setOpacity(0);
                    text.setText(colors[randIndex]);

                    Color c = Color.valueOf(colors[randIndex]);
                    // rgba is the current color with additional opacity when hover
                    String rgba = "rgba(" + min(255, c.getRed() * 255 + 50) + ","
                            + min(255, c.getGreen() * 255 + 50) + ","
                            + min(255, c.getBlue() * 255 + 50)
                            + ", 1.0)";

                    afterHover(spinner,
                            "-fx-background-color: " + colors[randIndex],
                            "-fx-background-color: " + rgba
                            );
//                    spinner.setStyle("-fx-background-color: " + colors[randIndex]);

                    FadeTransition ft = new FadeTransition(Duration.millis(800), text);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    mutux = false;
                });

                rotate.play();


            }
        });
        spinner.setId("board_side_button");
        return spinner;
    }


    public int getSpinnerVal(){
        return Integer.parseInt(sText.getText());
    }

    public void setSpinnerText(String s){
        sText.setText(s);
    }

    public Button makeDeck(Label text){
        Button deck = new Button();
        Collections.shuffle(curDeck);

        ImageView deckBackground = Helper.imageMaker("src/gamePlay/images/Deck.png", 100, 100);
        deck.setGraphic(deckBackground);


        deck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // write a lock, make sure only one animation at a time
                if (mutux) { return; }
                mutux = true;

                // flip card animation
                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Y_AXIS);
                rotate.setByAngle(180);
                rotate.setInterpolator(Interpolator.LINEAR);
                rotate.setCycleCount(1);
                rotate.setDuration(Duration.millis(750));
                rotate.setAutoReverse(true);

                rotate.setNode(deckBackground);
                rotate.setOnFinished(event1 -> {

                    text.setOpacity(0);
                    text.setText(String.valueOf(drawCard()));
                    FadeTransition ft = new FadeTransition(Duration.millis(800), text);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    mutux = false;
                });
                rotate.play();
            }
        });
        deck.setId("board_side_button");
        return(deck);
    }
    private int drawCard(){
        int curCard = curDeck.remove(curDeck.size()-1);
        drawnDeck.add(curCard);
        if(curDeck.size() == 0){
            curDeck = (ArrayList<Integer>) drawnDeck.clone();
            Collections.shuffle(curDeck);
            drawnDeck.clear();;
        }
        return curCard;
    }

    public int getDeckVal(){
        return Integer.parseInt(deckText.getText());
    }

    public void setDeckText(String s){
        deckText.setText(s);
    }

    public VBox getObjects(){
        VBox vBox = new VBox();
        vBox.getChildren().addAll(dButton, dText, sButton, sText, deckButton, deckText);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

}
