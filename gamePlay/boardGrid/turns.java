package boardGrid;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class turns {
    private ArrayList<String> playerList;
    private int currentIndex;
    private StackPane turnsBoard;

    public turns(boardScore bs){
        this.playerList = bs.getPlayerList();
        this.currentIndex = 0;
        this.turnsBoard = new StackPane();
    }

    public void next(){
        this.currentIndex = (this.currentIndex + 1) % this.playerList.size();

        try{
            // get HBox from turnBoard
            Node nodeHBox = this.turnsBoard.getChildren().get(1);
            if (nodeHBox instanceof HBox){
                // get Text inside the HBox
                Node nodeText = ((HBox) nodeHBox).getChildren().get(0);

                if (nodeText instanceof Text){
                    ((Text) nodeText).setText(
                            "Current Turn:\t" + this.playerList.get(this.currentIndex)
                    );
                }

            }
        }
        catch (Exception e){
            System.out.println("Have trouble updating turns display");
        }
    }

    public String getCurrentPlayer() { return this.playerList.get(this.currentIndex); }

    public StackPane displayTurns(){

        HBox textBoard = new HBox(10);
        Text turnInfo = new Text("Current Turn:\t" + this.playerList.get(this.currentIndex));
        turnInfo.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));

        textBoard.getChildren().addAll(turnInfo);
        textBoard.setAlignment(Pos.CENTER);

        Rectangle turnsBackground = new Rectangle();
        turnsBackground.setWidth(400);
        turnsBackground.setHeight(40);
        turnsBackground.setFill(Color.RED);

        this.turnsBoard.getChildren().add(turnsBackground);
        this.turnsBoard.getChildren().add(textBoard);
        this.turnsBoard.setAlignment(Pos.CENTER);

        return this.turnsBoard;
    }
}
