import javafx.scene.layout.*;

import java.util.ArrayList;


public class boardGrid {
    // grid pane
    protected static GridPane board = new GridPane();
    private static int height = 10, width = 10;
    private static boardScore currentScore;
    private static turns currentTurn;
    private static ArrayList<String> playerList;

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
                                getCellObject(currentScore, currentTurn),    // button or board element
                        i,  // x coordinate
                        j); // y coordinate
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
                currentCell.loadCellImage("images/fish.jpeg");
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

    public void updateCell(boardCell cell, int x, int y, StackPane object){
        // TODO: replace cell method
        try{
            board.getChildren().remove(x, y);
            board.add(
                    cell.getCellObject(currentScore, currentTurn),
                    x,
                    y
            );
        }
        catch (Exception ignored) {
            board.add(
                    cell.getCellObject(currentScore, currentTurn),
                    x,
                    y
            );
        }
    }

    public static HBox createScore(){
        return currentScore.scoreBox();
    }

    public static StackPane createTurns(){
        return currentTurn.displayTurns();
    }


    public static GridPane createBoard(ArrayList<String> players, ArrayList<ArrayList<StackPane>> cellTable){


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

        loadGrid(stackToCell(cellTable));   // load from a 2d array

        return board;
    }
}