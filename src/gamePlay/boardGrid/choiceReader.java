package gamePlay.boardGrid;

import Objects.Deck;
import Objects.Player;
import Objects.Tile;
import State.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class choiceReader {
    private boardGrid board;
    private List<Player> playerList;
    private Button spinner;
    private Button deck;
    private Button dice;
    private Button invButton;
    private List<Choice> choiceList;
    private ArrayList<Integer[]> cordList;
    private GameState gameState;

    public choiceReader(boardGrid board, List<Player> playerList, Button spinner, Button deck, Button dice, List<Choice> choiceList, Button invButton, GameState gameState){
        this.board = board;
        this.playerList = playerList;
        this.spinner = spinner;
        this.deck = deck;
        this.dice = dice;
        this.choiceList = choiceList;
        this.invButton = invButton;
        this.gameState = gameState;
    }

    private void nullActions(){
        for(int i = 0; i < cordList.size(); i++){
            board.getBoardCell(cordList.get(i)[0], cordList.get(i)[0]).setOnMouseClicked(null);
        }
        spinner.setOnAction(null);
        deck.setOnAction(null);
        dice.setOnAction(null);
        invButton.setOnAction(null);
    }

    private void parseList(){
        for(int i = 0; i < choiceList.size(); i++){
            if(choiceList.get(i) instanceof MoveChoice){
                move((MoveChoice) choiceList.get(i), i);
                continue;
            }
            if(choiceList.get(i) instanceof DrawCardChoice){
                draw((DrawCardChoice) choiceList.get(0), i);
                continue;
            }
            if(choiceList.get(i) instanceof PassChoice){
                System.out.println("We didn't implement a pass button");
                continue;
            }
            if(choiceList.get(i) instanceof WinChoice){
                //Stage primaryStage = spinner.getScene().getWindow();
            }
        }
    }

    private void move(MoveChoice m, int choice){
        Tile tile = m.getTile();
        StackPane pane = board.getBoardCell(tile.getX(), tile.getY());
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameState.progressGame(choice);
                //TODO: Update board from GameState
            }
        });
    }

    private void draw(DrawCardChoice c, int choice){
        deck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameState.progressGame(choice);
                //TODO: Update board from GameState
            }
        });
    }

}
