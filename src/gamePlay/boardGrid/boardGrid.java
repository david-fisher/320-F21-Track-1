package gamePlay.boardGrid;

import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class boardGrid {
    // grid pane
    protected static GridPane board = new GridPane();
    private static int height = 10, width = 10;
    private static boardScore currentScore;
    private static turns currentTurn;
    private static ArrayList<String> playerList;

    // load an arraylist to the grid
    private static void loadGrid(Stage primaryStage, ArrayList<ArrayList<boardCell>> table){
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
                                getCellObject(primaryStage, currentScore, currentTurn),    // button or board element
                        i,  // x coordinate
                        j   // y coordinate
                );
                setDrag(table.get(i).get(j));
            }
        }
    }

    // convert possible stackPane table to boardCell table
    // TODO: work with Binary-Brothers
    private static ArrayList<ArrayList<boardCell>> stackToCell(ArrayList<ArrayList<StackPane>> stackTable){
        ArrayList<ArrayList<boardCell>> cellTable = new ArrayList<>();

        for (int i = 0; i < stackTable.size(); i++){

            ArrayList<boardCell> currentRow = new ArrayList<>();
            for (int j = 0; j < stackTable.get(i).size(); j++){
                boardCell currentCell = new boardCell();
                currentCell.setPosition(i, j);
                if (stackTable.get(i).get(j) != null){  // load stack
                    currentCell.loadStack(stackTable.get(i).get(j));
                    currentRow.add(currentCell);
                    continue;
                }

                // if it's an empty stack pane, then create some default features
                currentCell.loadCellImage(i % 2 == 0? "src/gamePlay/images/fish.jpeg": "src/gamePlay/images/marius.jpeg");
                currentCell.setIndex(i + j * width + 1);
                currentRow.add(currentCell);

            }
            cellTable.add(currentRow);
        }
        return cellTable;
    }

    protected static void updatePlayerScore(String name, int amount){

        // TODO: checking winning or scoring condition
        boolean condition = true;
        if (condition){
            currentScore.updateScore(name, amount);
        }


    }

    public void updateCell(Stage primaryStage, boardCell cell, int x, int y, StackPane object){
        // TODO: replace cell method
        try{
            board.getChildren().remove(x, y);
            board.add(
                    cell.getCellObject(primaryStage, currentScore, currentTurn),
                    x,
                    y
            );
        }
        catch (Exception ignored) {
            board.add(
                    cell.getCellObject(primaryStage, currentScore, currentTurn),
                    x,
                    y
            );
        }
    }

    /*
    Will move whatever is in the top of the stackpane to another location
    */
    public static void movePiece(int origX, int origY, int destX, int destY){
        StackPane origin = getBoardCell(origX, origY);
        StackPane destination = getBoardCell(destX, destY);
        destination.getChildren().addAll(origin.getChildren().get(1));
    }

    /*
    ********************************
    get cell based on the coordinate
     */
    //TODO: This is the function that I need to call in order to do the stackpane animation for dragging
    public static StackPane getBoardCell(int x, int y){
        Node node = null;
        ObservableList<Node> childerns = board.getChildren();

        for (Node n : childerns){
            if (board.getRowIndex(n) == x && board.getColumnIndex(n) == y){
                node = n;
                break;
            }
        }

        return (node instanceof StackPane)? (StackPane) node : null;
    }

    // set drag motion for the cell
    protected static void setDrag(boardCell cell){
        /*
            from Binary-Brother Branch
         */
        cell.getStack().setCursor(Cursor.HAND);

        // stack was pressed:
        cell.getStack().setOnMousePressed((event -> {
            int orgX = cell.getPosition()[0]; int orgY = cell.getPosition()[1];

            StackPane current = cell.getStack();
            current.toFront();
        }));

        // stack was dragged
        cell.getStack().setOnMouseDragged((event -> {

        }));


    }

    public static HBox createScore(){
        return currentScore.scoreBox();
    }

    public static StackPane createTurns(){
        return currentTurn.displayTurns();
    }


    public static GridPane createBoard(Stage primaryStage, ArrayList<String> players, ArrayList<ArrayList<StackPane>> cellTable){


        width = cellTable.size();
        height = cellTable.get(0).size();

        int widthPercentage = 100 / width;
        int heightPercentage = 100 / height;

        playerList = new ArrayList<>();

        if (players == null){
            players  = new ArrayList<>();
            players.add(null);
        }

        playerList.addAll(players);

        // create local score and turns objects
        currentScore = new boardScore(playerList);
        currentTurn = new turns(currentScore);

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

        loadGrid(primaryStage, stackToCell(cellTable));   // load from a 2d array

        // testing update board cell
//        StackPane a = getBoardCell(3, 3);
//        a.getChildren().remove(0, 2);
//        a.getChildren().addAll(getBoardCell(1, 0).getChildren());
        //movePiece(3, 3, 4, 4);

        return board;
    }
}
