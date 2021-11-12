import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class boardGrid {
    // grid pane
    protected static GridPane board = new GridPane();
    private static int height = 10, width = 10;
    private static boardScore currentScore;

    // load an arraylist to the grid
    private static void loadGrid(ArrayList<ArrayList<boardCell>> table){
        /*
        table:
            [i] is width
            [j] is height
         */
        for (int i = 0; i < table.size(); i++){
            for (int j = 0; j < table.get(i).size(); j++){
                boardCell currentCell = table.get(i).get(j);
                if (currentCell == null) { continue; }  // empty cell element
                board.add(table.get(i).get(j).
                                getCellObject(null, board, currentScore, height, width),    // button or board element
                        i,  // x coordinate
                        j); // y coordinate
            }
        }
    }

    // get an imageView from an image file
    private static ImageView imageFromFile(String fileName){
        ImageView imageView = new ImageView();

        try {   // try open the image file
            InputStream imageFile = new FileInputStream(fileName);  // load from file
            Image image = new Image(imageFile);
            imageView.setImage(image);

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file: " + fileName);
            e.printStackTrace();
        }

        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        return imageView;
    }

    // generate cell is a function for testing only
    private static ArrayList<ArrayList<boardCell>> generateCell(int width, int height){
        ArrayList<ArrayList<boardCell>> cellTable = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            ArrayList<boardCell> currentRow = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                char a = 'A';
                int aChar = a;
                char c = (char) (aChar + i + j);
                boardCell currentCell = new boardCell(
                        Character.toString(c),
                        imageFromFile("src/images/fish.jpeg")   // the working dir is the project root directory
                );
                currentRow.add(currentCell);
            }
            cellTable.add(currentRow);
        }
        return cellTable;
    }

    protected static void updateScore(){

        // TODO: checking winning or scoring condition
        boolean condition = true;
        if (condition){
            currentScore.addOne();
        }


    }

    public void updateCell(boardCell c, int x, int y){
        // TODO: replace cell method
        try{
            board.getChildren().remove(x, y);
        }
        catch (Exception ignored) {
            board.add(
                    c.getCellObject(null, board, currentScore, height, width),
                    x,
                    y
            );
        }
    }

    public static VBox createScore(){
        return currentScore.scoreBox();
    }


    public static GridPane createBoard(String userName, int h, int w){


        height = h;
        width = w;

        int widthPercentage = 100 / width;
        int heightPercentage = 100 / height;

        currentScore = new boardScore(userName);

        // column constraints
        for (int i = 0; i < width; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(widthPercentage);
            board.getColumnConstraints().
                    add(column);
        }

        // row constraints
        for (int j  = 0; j < height; j++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(heightPercentage);
            board.getRowConstraints().
                    add(row);
        }

        loadGrid(generateCell(width, height));   // load from an arraylist

        // TODO: preset board size

        return board;
    /*  test:
        board.add(Helper.ButtonMaker("a", null), 0, 0);
        board.add(Helper.ButtonMaker("b", null), 0, 2);
        board.add(Helper.ButtonMaker("c", null), 1, 0);
        board.add(Helper.ButtonMaker("d", null), 2, 2);
    */

//        Scene scene = new Scene(board);
//        return scene;
    }
}
