package boardGrid;
import boardGrid.Helpers.Helper;
import javafx.scene.Scene;
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
                char c = (char) (aChar + i + j * height);
                board.add(Helper.ButtonMaker(Character.toString(c), null), i, j);
            }
        }

//
//        board.add(Helper.ButtonMaker("a", null), 0, 0);
//        board.add(Helper.ButtonMaker("b", null), 0, 2);
//        board.add(Helper.ButtonMaker("c", null), 1, 0);
//        board.add(Helper.ButtonMaker("d", null), 2, 2);


        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        board.getColumnConstraints().addAll(column1, column2);

        Scene scene = new Scene(board);
        return scene;
    }




}
