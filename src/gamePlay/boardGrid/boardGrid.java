package gamePlay.boardGrid;

import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.*;

import java.util.ArrayList;


public class boardGrid {
    // grid pane
    private static double orgSceneX, orgSceneY, xTemp;
    private static StackPane shapeToDelete;

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
                        j); // y coordinate
            }
        }
        StackPane child = createRectangle(0,0);
        board.add(child, 0,0);
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

    public static void dragNDrop_StackPane(StackPane sp, int x, int y) {
        // Drag n' Drop Interaction *******************************************************
        sp.setCursor(Cursor.HAND);

        sp.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            xTemp = orgSceneX;

            StackPane c = (StackPane) (t.getSource());
            c.toFront();
        });

        sp.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            StackPane c = (StackPane) (t.getSource());

            c.setTranslateX(c.getTranslateX() + offsetX);
            c.setTranslateY(c.getTranslateY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        sp.setOnMouseReleased((t) -> {
            StackPane c = (StackPane) (t.getSource());

            double tile_size = 80;
            double snapX = (c.getTranslateX()) % tile_size;
            if (snapX > tile_size/2){
                snapX = (c.getTranslateX()) - snapX + tile_size;
            }
            else {
                snapX = (c.getTranslateX()) - snapX;
            }
            if (t.getSceneX() >= 100 && t.getSceneX() < (100 + x*tile_size)) {
                c.setTranslateX(snapX);
            }
            else {
                c.setTranslateX(0);
            }

            double snapY = (c.getTranslateY()) % tile_size;
            if (snapY > tile_size/2){
                snapY = (c.getTranslateY()) - snapY + tile_size;
            }
            else {
                snapY = (c.getTranslateY()) - snapY;
            }

            if (t.getSceneY() >= 100 && t.getSceneY() < (100 + y*tile_size)) {
                c.setTranslateY(snapY);
            }
            else {
                c.setTranslateY(0);
            }

        });
    }

    public static StackPane createRectangle(int x, int y) {
        Rectangle rectangle = new Rectangle(80,80);
        TextField text = new TextField ("+1");
        //Making the TextField transparent, so we dont see the whole input box
        text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;-fx-focus-color: transparent;");
        text.setMaxWidth(60);
        text.setAlignment(Pos.CENTER);

        //Adding shadow for text, for better readability
        DropShadow shadow = new DropShadow();
        shadow.setSpread(0.6);
        shadow.setHeight(5);
        shadow.setWidth(5);
        text.setEffect(shadow);

        //Create a stackPane for TextField and Shape
        StackPane layout = new StackPane();
        layout.getChildren().addAll(rectangle,text);
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY) ) { //&& mouseEvent.getClickCount() >= 2){ //enable to select with double-click instead
                    System.out.println("shape clicked");
                    shapeToDelete = layout;
                }
            }
        });
        layout.setMaxHeight(50);
        layout.setMaxWidth(50);
        layout.setLayoutX(10);
        layout.setLayoutY(180);
        dragNDrop_StackPane(layout,x, y);
        return layout;
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
        StackPane a = getBoardCell(3, 3);
        a.getChildren().remove(0, 2);
        a.getChildren().addAll(getBoardCell(1, 0).getChildren());

        return board;
    }
}
