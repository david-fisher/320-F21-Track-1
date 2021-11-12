import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class boardScore {
    private int score;
    private String player;

    public boardScore(String name){
        this.score = 0;
        this.player = name;
    }

    public void addOne() { this.score++; }

    public void updateScore(int s){ this.score = s; }

    public VBox scoreBox(){
        VBox box = new VBox();

        Text number = new Text(String.valueOf(this.score));
        number.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Text user = new Text(this.player == null? "Marius" : this.player);
        user.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 40));

        box.getChildren().addAll(user, number);
        return box;
    }

    // return a score object that show on the stage
    public Text scoreObject(){
        return new Text(String.valueOf(this.score));
    }
}
