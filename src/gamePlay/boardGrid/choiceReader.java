package gamePlay.boardGrid;

import Objects.Deck;
import Objects.Player;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class choiceReader {
    private boardGrid board;
    private List<Player> playerList;
    private Button spinner;
    private Button deck;
    private Button dice;
    private Button invButton;

    private ArrayList<Integer[]> cordList;
    private ArrayList<Deck> invDeck;

    public choiceReader(boardGrid board, List<Player> playerList, Button spinner, Button deck, Button dice){
        this.board = board;
        this.playerList = playerList;
        this.spinner = spinner;
        this.deck = deck;
        this.dice = dice;
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
}
