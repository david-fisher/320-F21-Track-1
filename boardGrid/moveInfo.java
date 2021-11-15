import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class moveInfo {
    private String description;

    public moveInfo(String description){
        this.description = description;
    }

    public VBox getMoveInfo(){

        if (this.description == null){
            this.description = "No information";
        }

        Text title = new Text("Current Move");
        Text info = new Text(this.description);

        info.setWrappingWidth(200);

        title.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 25));
        info.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        VBox infoBoard = new VBox(10);
        infoBoard.getChildren().addAll(title,info);

        return infoBoard;
    }

}
