package Helpers;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class cell {
    private ImageView imageView;
    private String name;
    private Integer cellHeight;
    private Integer cellWidth;
    private Integer x;
    private Integer y;

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

    // return an array size of two
    public Integer[] getPosition(){ return new Integer[]{this.x, this.y}; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    public ImageView getCellObject(String imageFile){
        String fileName = (imageFile == null)? "images/fish.jpeg" : imageFile;
        ImageView icon = Helper.imageMaker(fileName, 70, 70);

//        icon.fitWidthProperty().bind();

        assert icon != null;
        icon.setOnMouseClicked(
                e -> {  // TODO: set mouse click actions
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
