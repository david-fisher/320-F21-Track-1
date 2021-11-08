package boardGrid.Helpers;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class cell {
    private ImageView imageView;
    private String name;
    private Integer cellHeight;
    private Integer cellWidth;

    public cell(String name, ImageView imageView){
        this.name = name;
        this.imageView = imageView;
    }

    public cell(String name, ImageView imageView, int height, int width){
        this.name = name;
        this.imageView = imageView;
        this.cellHeight = height;
        this.cellWidth = width;
    }

    public String getName() { return this.name; }
    public ImageView getImageView() { return this.imageView; }

    public Button getCellButton(){
        Button button = Helper.ButtonMaker(this.name, null);
        Helper.adjustButtonSize(button,
                (this.cellHeight == null)? 70: this.cellHeight, // default size 70
                (this.cellWidth == null)? 70: this.cellWidth
        );
        if (this.imageView != null) { button.setGraphic(this.imageView); }
        return button;
    }

}
