package boardGrid;

import Helpers.Helper;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class boardCell {
    private final ImageView imageView;
    private final String name;
    private Integer cellIndex;
    private Integer x;
    private Integer y;
    private ImageView icon;
    private StackPane stack;

    public boardCell(){
        this.name = null;
        this.imageView = null;
        this.cellIndex = null;
        this.stack = null;
    }

    public boardCell(String name){
        this.name = name;
        this.imageView = null;
        this.cellIndex = null;
        this.stack = null;
    }

    public boardCell(String name, ImageView imageView){
        this.name = name;
        this.imageView = imageView;
        this.cellIndex = null;
        this.stack = null;
    }

    public void setIndex(int index) { this.cellIndex = index; }
    public Text setText(){
        // update index in the front
        Text index = new Text(this.cellIndex == null? "empty" : this.cellIndex.toString());
        index.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 20));
        return index;
    }

    public void loadCellImage(String fileName){
        if (fileName == null) { return; }   // check null

        icon = Helper.imageMaker(fileName, 70, 70);
    }


    public String getCellName() { return this.name; }
    public ImageView getCellImageView() { return this.imageView; }

    // return an array size of two
    public Integer[] getPosition(){ return new Integer[]{this.x, this.y}; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    // load a stack pane to the cell
    public void loadStack(StackPane s){
        this.stack = s;
    }

    public StackPane getCellObject(boardScore score, turns currentTurn){
        if (stack == null){ // if binary-brother can provide the cell stack pane for us, we don't have to recreate it
            stack = new StackPane();

            Rectangle square = new Rectangle(80, 80);
            if (this.x == null){
                square.setFill(Color.GREEN);
            }
            else{
                square.setFill(this.x % 2 == 0?
                        (this.y % 2 == 0)?
                                Color.gray(0.3) : Color.gray(0.8)
                        : (this.y % 2 == 0) ?
                        Color.gray(0.8) : Color.gray(0.3));
            }

            stack.getChildren().add(square);    // background
            stack.getChildren().add(icon);  // image
            stack.getChildren().add(setText());
        }


        stack.setOnMouseClicked(
                e -> {  // set mouse click actions

                    /*
                    score.addOne(String playerName)
                    score.updateScore(String playerName, int amount)
                     */
                    score.addOne(currentTurn.getCurrentPlayer());
                    currentTurn.next();
                }
        );

        stack.setOnDragDetected( // drag starts
                e -> {  // TODO: set drag motion
                    icon.setVisible(false);
                    score.updateScore(currentTurn.getCurrentPlayer(), -1);
                    currentTurn.next();
                }
        );

        // TODO: Drag motion implementation

        return stack;
    }


}
