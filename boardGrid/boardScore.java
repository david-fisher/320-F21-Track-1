import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class boardScore {
    private int score1, score2;
    private String player1, player2;

    public boardScore(String player1, String player2){
        this.score1 = 0;
        this.score2 = 0;
        this.player1 = player1 == null? "Player1" : player1;
        this.player2 = player2 == null? "Player2" : player2;
    }

    public void addOnePlayer1() { this.score1++; }
    public void addOnePlayer2() { this.score2++; }

    public void updatePlayer1Score(int s) { this.score1 += s; }
    public void updatePlayer2Score(int s) { this.score2 += s; }

    public HBox scoreBox(){

        VBox player1Board = new VBox();
        VBox player2Board = new VBox();

        Text num1 = new Text(String.valueOf(this.score1));
        Text num2 = new Text(String.valueOf(this.score2));
        Text user1 = new Text(this.player1);
        Text user2 = new Text(this.player2);

        num1.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));
        num2.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));
        user1.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 40));
        user2.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 40));

        player1Board.getChildren().addAll(user1, num1);
        player1Board.setAlignment(Pos.CENTER);
        player2Board.getChildren().addAll(user2, num2);
        player2Board.setAlignment(Pos.CENTER);


        // TODO: fill color for score board bounds
//        Rectangle rectangle = new Rectangle(50, 40);
//        HBox sBoard = new HBox(rectangle);
        HBox sBoard = new HBox();
        sBoard.getChildren().addAll(player1Board, player2Board);
        sBoard.setSpacing(10);

        return sBoard;
    }

//    // return a score object that show on the stage
//    public Text scoreObject(){
//        return new Text(String.valueOf(this.score));
//    }
}
