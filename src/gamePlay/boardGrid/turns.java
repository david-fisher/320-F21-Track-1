package gamePlay.boardGrid;

import State.GameState;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class turns {
    private ArrayList<String> playerList;
    private int currentIndex;
    private StackPane turnsBoard;
    private ArrayList<String> colors;
    private int colorIndex;
    private GameState gameState;

    public turns(boardScore bs /*, GameState gameState*/){
        this.playerList = bs.getPlayerList();
        this.currentIndex = 0;
        this.turnsBoard = new StackPane();
        this.colors = bs.getColorList();
        this.colorIndex = 0;
        //this.gameState = gameState;
    }

    public void next(){
        this.currentIndex = (this.currentIndex + 1) % this.playerList.size();
        this.colorIndex = (this.colorIndex + 1) % this.colors.size();

        try{
            // get HBox from turnBoard
            Node nodeLabel = this.turnsBoard.getChildren().get(0);
            if (nodeLabel instanceof Label){
                // get Text inside the Label
                ((Label) nodeLabel).setText(getCurrentText());
                // change background color
                ((Label) nodeLabel).setStyle(this.colors.get(this.colorIndex) + "-fx-padding: 10px;");
            }
        }
        catch (Exception e){
            System.out.println("Have trouble updating turns display");
        }
    }

    private String getCurrentText(){
        String output = "Current Turn:\t";
        output += this.playerList.get(this.currentIndex);
        return output;
    }

    public String getCurrentPlayer() { /*return this.gameState.getCurPlayer().getAttributes().get("name");*/ return this.playerList.get(this.currentIndex); }

    public StackPane displayTurns(){

        Label turnInfo = new Label(getCurrentText());
        turnInfo.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));
        turnInfo.setTextAlignment(TextAlignment.CENTER);

        turnInfo.setStyle(this.colors.get(this.colorIndex) + "-fx-padding: 10px;");

        this.turnsBoard.getChildren().add(turnInfo);
        this.turnsBoard.setAlignment(Pos.CENTER);

        return this.turnsBoard;
    }
}
