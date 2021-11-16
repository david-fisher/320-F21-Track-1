import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class boardScore {
    private Hashtable<String, Integer> playerScore;
    private ArrayList<String> playerList;
    private HBox scoreBoard;


    public boardScore(ArrayList<String> nameList){
        this.playerScore = new Hashtable<>();
        this.playerList = new ArrayList<>();
        this.scoreBoard = new HBox();

        int lastUsedNum = 0;
        for (String playerName : nameList) {
            String name = playerName == null ?
                    "player" + String.valueOf(++lastUsedNum)
                    : playerName;

            this.playerScore.put(name, 0);
        }

        Set<String> playersSet = this.playerScore.keySet();
        this.playerList.addAll(playersSet);
    }

    protected ArrayList<String> getPlayerList() { return this.playerList; }

    // showUpdates on UI
    private boolean showUpdates(String key){
        try {
            int playerIndex = this.playerList.indexOf(key);

            Node nodeOut = this.scoreBoard.getChildren().get(playerIndex);
            if (nodeOut instanceof VBox){
                Node nodeIn = ((VBox) nodeOut).getChildren().get(1);
                if (nodeIn instanceof Text){
                    ((Text) nodeIn).setText(
                            this.playerScore.get(key).toString()
                    );
                }
            }

            return true;
        }
        catch (Exception e){    // something wrong happen
            System.out.println("Cannot update score of " + key);
            return false;
        }

    }

    // return true if add successfully
    public boolean addOne(String playerName){
        if (this.playerScore.get(playerName) == null) { return false; }
        this.playerScore.put(playerName, this.playerScore.get(playerName) + 1);

        return showUpdates(playerName);
    }

    // return true if add successfully
    public boolean updateScore(String playerName, int amount){
        if (this.playerScore.get(playerName) == null) { return false; }
        this.playerScore.put(playerName, this.playerScore.get(playerName) + amount);

        return showUpdates(playerName);
    }


    public HBox scoreBox(){

        for(String player : this.playerList) {
            VBox currentBoard = new VBox();

            Text number = new Text(String.valueOf(this.playerScore.get(player)));
            Text user = new Text(player);

            number.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));
            user.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 40));

            currentBoard.getChildren().addAll(user, number);
            currentBoard.setAlignment(Pos.CENTER);

            this.scoreBoard.getChildren().add(currentBoard);
        }

        // TODO: fill color for score board bounds
        this.scoreBoard.setSpacing(10);

        return this.scoreBoard;
    }

}
