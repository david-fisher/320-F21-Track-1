package boardGrid;

import Helpers.Helper;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class moveInfo {
    private String description;
    private StackPane moveInfoBoard;
    private ImageView backgroundImage;

    public moveInfo(String description){
        this.description = description;
    }

    public StackPane getMoveInfo(){
        moveInfoBoard = new StackPane();

        if (this.description == null){
            this.description = "No information";
        }

        Text title = new Text("Current Move");
        Text info = new Text(this.description);

        info.setWrappingWidth(200);

        title.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 25));
        info.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        VBox infoVBox = new VBox(25);
        infoVBox.getChildren().addAll(title,info);
        infoVBox.setAlignment(Pos.TOP_CENTER);

        String backgroundFile = "gamePlay/images/infoBackground.png";
        backgroundImage = Helper.imageMaker(backgroundFile, 200, 250);


        moveInfoBoard.getChildren().add(backgroundImage);
        moveInfoBoard.getChildren().add(infoVBox);
        moveInfoBoard.setAlignment(Pos.TOP_CENTER);

        return moveInfoBoard;
    }

}
