package boardGrid;

import Helpers.Helper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class RNG {
    Button dButton;
    Button sButton;
    Button deckButton;
    Text dText;
    Text sText;
    Text deckText;
    ArrayList<Integer> curDeck;
    ArrayList<Integer> drawnDeck;

    public RNG(int diceMin, int diceMax, String[] colors, ArrayList<Integer> deck){
        this.dText = textMaker();
        this.dButton = makeDie(diceMin, diceMax, this.dText);
        this.sText = textMaker();
        this.sButton = makeSpinner(colors, sText);
        this.curDeck = (ArrayList<Integer>) deck.clone();
        this.drawnDeck = new ArrayList<Integer>();
        this.deckText = textMaker();
        this.deckButton = makeDeck(deckText);
    }

    private Button makeDie(int diceMin, int diceMax, Text value){
        Button dice = new Button();
        dice.setGraphic(Helper.imageMaker("gamePlay/images/Dice.png", 100, 100));

        dice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int Num = (int) ((Math.random() * (diceMax-diceMin)) + diceMin);
                value.setText(String.valueOf(Num));
            }
        });
        dice.setId("board_side_button");
        return dice;
    }

    private Text textMaker(){
        Text text = new Text("--");
        text.setFont(Font.font("Helvetica", 25));
        return text;
    }

    public int getDiceVal(){
        return Integer.parseInt(dText.getText());
    }
    public void setDiceText(String s){
        dText.setText(s);
    }

    private Button makeSpinner(String[] colors, Text text){
        Random rand = new Random();
        Button spinner = new Button();
        spinner.setGraphic(Helper.imageMaker("gamePlay/images/Spinner.png", 100, 100));
        spinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.setText(colors[(rand.nextInt(colors.length))]);
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

    public Button makeDeck(Text text){
        Button deck = new Button();
        Collections.shuffle(curDeck);
        deck.setGraphic(Helper.imageMaker("gamePlay/images/Deck.png", 100, 100));
        deck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.setText(String.valueOf(drawCard()));
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
