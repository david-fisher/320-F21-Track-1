import Helpers.Helper;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    private Integer cellHeight;
    private Integer cellWidth;
    private Integer x;
    private Integer y;
    private ImageView icon;
    private StackPane stack;

    public boardCell(String name, ImageView imageView){
        this.name = name;
        this.imageView = imageView;
        this.cellIndex = null;
    }

    public boardCell(String name, ImageView imageView, int height, int width){
        this.name = name;
        this.imageView = imageView;
        this.cellHeight = height;
        this.cellWidth = width;
        this.cellIndex = null;
    }

    public void setIndex(int index) { this.cellIndex = index; }

    public String getName() { return this.name; }
    public ImageView getImageView() { return this.imageView; }

    // return an array size of two
    public Integer[] getPosition(){ return new Integer[]{this.x, this.y}; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    public StackPane getCellObject(String imageFile, GridPane board, boardScore score, int height, int width){
        stack = new StackPane();

        Rectangle square = new Rectangle(80, 80);
        square.setFill(Color.BLUE);


        String fileName = (imageFile == null)? "images/fish.jpeg" : imageFile;
        icon = Helper.imageMaker(fileName, 70, 70);
//        ImageView icon = Helper.imageFromFile(fileName);
//        icon.setPreserveRatio(true);

        // TODO: figure out if the resizable issue is here
//        icon.fitWidthProperty().bind(board.widthProperty().divide(width));
//        icon.fitHeightProperty().bind(board.heightProperty().divide(height));



        // update index in the front
        Text index = new Text(this.cellIndex == null? "empty" : this.cellIndex.toString());
        index.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 20));

        stack.getChildren().add(square);    // background
        stack.getChildren().add(icon);  // image
        stack.getChildren().add(index);

        stack.setOnMouseClicked(
                e -> {  // TODO: set mouse click actions

                    score.addOne("Marius");
                    score.updateScore("player1", 10);
//                    System.out.println(e.toString() + " is clicked");
                }
        );

        stack.setOnDragDetected( // drag starts
                e -> {  // TODO: set drag motion
                    icon.setVisible(false);
                }
        );



        return stack;
    }


}
