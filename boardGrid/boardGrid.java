import Helpers.cell;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class boardGrid {
    // grid pane
    private static GridPane board = new GridPane();

    // load an arraylist to the grid
    private static void loadGrid(ArrayList<ArrayList<cell>> table){
        /*
        table:
            [i] is width
            [j] is height
         */
        for (int i = 0; i < table.size(); i++){
            for (int j = 0; j < table.get(i).size(); j++){
                cell currentCell = table.get(i).get(j);
                if (currentCell == null) { continue; }  // empty cell element
                board.add(table.get(i).get(j).
                                getCellObject(null),    // button or board element
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
    private static ArrayList<ArrayList<cell>> generateCell(int width, int height){
        ArrayList<ArrayList<cell>> cellTable = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            ArrayList<cell> currentRow = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                char a = 'A';
                int aChar = a;
                char c = (char) (aChar + i + j);
                cell currentCell = new cell(
                        Character.toString(c),
                        imageFromFile("src/images/fish.jpeg")   // the working dir is the project root directory
                );
                currentRow.add(currentCell);
            }
            cellTable.add(currentRow);
        }
        return cellTable;
    }

    public static void updateCell(cell c, int x, int y){
        // TODO: replace cell method
        try{
            board.getChildren().remove(x, y);
        }
        catch (Exception ignored) {
            board.add(
                    c.getCellObject(null),
                    x,
                    y
            );
        }
    }


    public static Scene makeScene(Stage primaryStage, int height, int width){

        int widthPercentage = 100 / width;
        int heightPercentage = 100 / height;



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

    /*  test:
        board.add(Helper.ButtonMaker("a", null), 0, 0);
        board.add(Helper.ButtonMaker("b", null), 0, 2);
        board.add(Helper.ButtonMaker("c", null), 1, 0);
        board.add(Helper.ButtonMaker("d", null), 2, 2);
    */

        Scene scene = new Scene(board);
        return scene;
    }




}
