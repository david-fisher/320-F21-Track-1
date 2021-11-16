import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class turns {
    private ArrayList<String> playerList;
    private int currentIndex;
    private HBox turnsBoard;

    public turns(boardScore bs){
        this.playerList = bs.getPlayerList();
        this.currentIndex = 0;
        this.turnsBoard = new HBox(10);
    }

    public void next(){
        this.currentIndex = (this.currentIndex + 1) % this.playerList.size();

        try{
            Node node = this.turnsBoard.getChildren().get(0);
            if (node instanceof Text){
                ((Text) node).setText(
                        "Current Turn: " + this.playerList.get(this.currentIndex)
                );
            }
        }
        catch (Exception e){
            System.out.println("Have trouble updating turns display");
        }
    }

    public String getCurrentPlayer() { return this.playerList.get(this.currentIndex); }

    public HBox displayTurns(){
        Text turnInfo = new Text("Current Turn: " + this.playerList.get(this.currentIndex));
        turnInfo.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));

        this.turnsBoard.getChildren().addAll(turnInfo);
        this.turnsBoard.setAlignment(Pos.CENTER);

        return this.turnsBoard;
    }
}
