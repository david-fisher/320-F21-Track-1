package boardGrid;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    public int getPlayerScoreByName(String name) { return this.playerScore.get(name); }

    // showUpdates on UI
    private boolean showUpdates(String key){
        try {
            int playerIndex = this.playerList.indexOf(key);

            // get a stack of one of the score board
            Node nodeStack = this.scoreBoard.getChildren().get(playerIndex);
            if (nodeStack instanceof StackPane){
                // get VBox inside the stack pane
                Node nodeVBox = ((StackPane) nodeStack).getChildren().get(1);
                if (nodeVBox instanceof VBox){
                    // get Text view inside the VBox
                    Node nodeText = ((VBox) nodeVBox).getChildren().get(1);
                    if (nodeText instanceof Text){
                        // update Text with the current score
                        ((Text) nodeText).setText(
                                this.playerScore.get(key).toString()
                        );
                    }
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

        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.LIME);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        int colorIndex = 0;

        for(String player : this.playerList) {
            StackPane localBoard = new StackPane();

            VBox currentVBox = new VBox();

            Text number = new Text(String.valueOf(this.playerScore.get(player)));
            Text user = new Text(player);
            number.setFill(Color.MIDNIGHTBLUE);

            number.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 40));
            user.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));

            currentVBox.getChildren().addAll(user, number);
            currentVBox.setAlignment(Pos.CENTER);

            Rectangle currentBackground = new Rectangle();
            currentBackground.setHeight(100);
            currentBackground.setWidth(90);
            currentBackground.setFill(colorList.get(colorIndex));
            colorIndex = (colorIndex + 1) % colorList.size();

            localBoard.getChildren().add(currentBackground);
            localBoard.getChildren().add(currentVBox);
            localBoard.setAlignment(Pos.CENTER);

            this.scoreBoard.getChildren().add(localBoard);
        }

        // TODO: fill color for score board bounds
        this.scoreBoard.setSpacing(10);

        return this.scoreBoard;
    }

}
