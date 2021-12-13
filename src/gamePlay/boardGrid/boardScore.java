package gamePlay.boardGrid;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class boardScore {
    private Hashtable<String, Integer> playerScore;
    private ArrayList<String> playerList;
    private HBox scoreBoard;
    private ArrayList<String> colorList = new ArrayList<>();


    public boardScore(ArrayList<String> nameList){
        this.playerScore = new Hashtable<>();
        this.playerList = new ArrayList<>();
        this.scoreBoard = new HBox();

        // all possible color background for the scoreboard and turnboard are here
        colorList.add("-fx-background-color: coral; ");
        colorList.add("-fx-background-color: #3b8eba; ");
        colorList.add("-fx-background-color:  #c796a0; ");

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

    public ArrayList<String> getColorList() { return this.colorList; }

    protected ArrayList<String> getPlayerList() { return this.playerList; }
    public int getPlayerScoreByName(String name) { return this.playerScore.get(name); }

    // showUpdates on UI
    private boolean showUpdates(String key){
        try {
            int playerIndex = this.playerList.indexOf(key);

            // get a stack of one of the score board
            Node nodeLabel = this.scoreBoard.getChildren().get(playerIndex);
            if (nodeLabel instanceof Label){
                // get label inside the hbox
                ((Label) nodeLabel).setText(getLabelText(key));
            }

        }
        catch (Exception e){    // something wrong happen
            System.out.println("Cannot update score of " + key);
            return false;
        }
        return true;
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

    private String getLabelText(String key){
        String scoreLocal = this.playerScore.get(key).toString();   // score
        String nameLocal = key; // name

        return (scoreLocal + "\r\n" + nameLocal);
    }

    public HBox scoreBox(){

        int colorIndex = 0;

        for(String player : this.playerList) {

            String scoreText = getLabelText(player);
            Label scoreLabel = new Label(scoreText);
            scoreLabel.setTextAlignment(TextAlignment.CENTER);

            scoreLabel.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 40));
            scoreLabel.setStyle(colorList.get(colorIndex) + "-fx-padding: 10px;");
            colorIndex = (colorIndex + 1) % colorList.size();

            this.scoreBoard.getChildren().add(scoreLabel);
        }

        // TODO: fill color for score board bounds
//        this.scoreBoard.setSpacing();

        return this.scoreBoard;
    }

}
