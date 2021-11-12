import Helpers.Helper;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class boardCell {
    private final ImageView imageView;
    private final String name;
    private Integer cellHeight;
    private Integer cellWidth;
    private Integer x;
    private Integer y;
    private ImageView icon;

    public boardCell(String name, ImageView imageView){
        this.name = name;
        this.imageView = imageView;
    }

    public boardCell(String name, ImageView imageView, int height, int width){
        this.name = name;
        this.imageView = imageView;
        this.cellHeight = height;
        this.cellWidth = width;
    }

    public String getName() { return this.name; }
    public ImageView getImageView() { return this.imageView; }

    // return an array size of two
    public Integer[] getPosition(){ return new Integer[]{this.x, this.y}; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    public ImageView getCellObject(String imageFile, GridPane board, boardScore score, int height, int width){
        String fileName = (imageFile == null)? "images/fish.jpeg" : imageFile;
        icon = Helper.imageMaker(fileName, 70, 70);
//        ImageView icon = Helper.imageFromFile(fileName);
//        icon.setPreserveRatio(true);

        // TODO: figure out if the resizable issue is here
//        icon.fitWidthProperty().bind(board.widthProperty().divide(width));
//        icon.fitHeightProperty().bind(board.heightProperty().divide(height));

        assert icon != null;
        icon.setOnMouseClicked(
                e -> {  // TODO: set mouse click actions
                    score.addOne();
                    System.out.println(e.toString() + " is clicked");
                }
        );

        icon.setOnDragDetected( // drag starts
                e -> {  // TODO: set drag motion
                    icon.setVisible(false);
                }
        );

        return icon;
    }


}
