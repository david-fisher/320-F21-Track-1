package boardGrid;
import boardGrid.Helpers.Helper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class boardGrid {


    public static Scene makeScene(Stage primaryStage, int height, int width){

        int widthPercentage = 100 / width;
        int heightPercentage = 100 / height;

        // grid pane
        GridPane board = new GridPane();

        for (int i = 0; i < width; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(widthPercentage);
            board.getColumnConstraints().add(column);
        }

        for (int j = 0; j < height; j++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(heightPercentage);
            board.getRowConstraints().add(row);
        }

        // add button
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                char a = 'A';
                int aChar = a;
                char c = (char) (aChar + i + j);
                Button b = Helper.ButtonMaker(Character.toString(c), null);
                board.add(Helper.adjustButtonSize(b, 70, 70), i, j);
            }
        }

//
//        board.add(Helper.ButtonMaker("a", null), 0, 0);
//        board.add(Helper.ButtonMaker("b", null), 0, 2);
//        board.add(Helper.ButtonMaker("c", null), 1, 0);
//        board.add(Helper.ButtonMaker("d", null), 2, 2);

        Scene scene = new Scene(board);
        return scene;
    }




}
