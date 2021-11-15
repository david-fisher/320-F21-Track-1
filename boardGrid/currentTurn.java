import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class currentTurn {
    private String currentPlayer;

    public currentTurn(String user){
        this.currentPlayer = user;
    }

    // update current turn
    public void updateTurn(String user){
        this.currentPlayer = user;
    }

    public HBox getCurrentTurn(){
        Text turnInfo = new Text("Current Turn: " + this.currentPlayer);
        turnInfo.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));

        HBox cTurnBoard = new HBox(10);
        cTurnBoard.getChildren().addAll(turnInfo);
        cTurnBoard.setAlignment(Pos.CENTER);

        return cTurnBoard;
    }
}
