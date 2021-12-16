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

    public choiceReader(boardGrid board, Button spinner, Button deck, Button dice,  Button invButton, GameState gameState){
        update(board, spinner, deck, dice,  invButton, gameState);
    }

    public void update(boardGrid board, Button spinner, Button deck, Button dice, Button invButton, GameState gameState){
        this.board = board;
        this.playerList = gameState.getPlayers();
        this.spinner = spinner;
        this.deck = deck;
        this.dice = dice;
        this.choiceList = gameState.getChoices();
        this.invButton = invButton;
        this.gameState = gameState;
        cordList.clear();
        for(int i = 0; i < playerList.size(); i++){
            Integer cord[] = {playerList.get(i).getTile().getX(), playerList.get(i).getTile().getY()};
            cordList.add(cord);
        }
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
                changeBoard();
            }
        });
    }

    private void draw(DrawCardChoice c, int choice){
        deck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameState.progressGame(choice);
            }
        });
    }

    private void invChoice(PlayCardChoice p, int choice){
        invButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if()
            }
        });
    }

    private void changeBoard(){
        for(int i = 0; i < playerList.size(); i++){
            board.movePiece((int) cordList.get(i)[0], (int) cordList.get(i)[1], playerList.get(i).getTile().getX(), playerList.get(i).getTile().getY());
            board.getBoardCell(playerList.get(i).getTile().getX(), playerList.get(i).getTile().getY()).setOnMouseClicked(null);
            board.updatePlayerScore(playerList.get(i).getAttributes().get("name"), playerList.get(i).getScore());
        }

        nullActions();
        parseList();
    }
}
