package Helpers;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Helper {
    public static Button ButtonMaker(String text, Font f){
        Button button = new Button();
        button.setText(text);
        button.setPrefWidth(300);
        //button.setPrefHeight(100);
        button.setFont(f == null? Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30) : f);

        return button;
    }

    public static Button adjustButtonSize(Button button, int height, int width){
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        return button;
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

        return imageView;
    }

    // create an imageview from a file and preset its size
    public static ImageView imageMaker(String fileName, int height, int width){

        ImageView currentImage = imageFromFile(fileName);
        if (currentImage == null) { return null; }

        currentImage.setFitHeight(height);
        currentImage.setFitWidth(width);

        return currentImage;
    }

    public static Games[] makeGames() {

        Games game1 = new Games("Life", "1:23:45");
        Games game2 = new Games("Life 2", "1:23:45");
        Games game3 = new Games("250 With Marius", "1:23:45");
        Games game4 = new Games("Standing up and saying your name to the rest of the class", "1:23:45");
        Games game5 = new Games("Track 1: Total War", "1:23:45");
        Games game6 = new Games("Finally Setting Up Github", "1:23:45");
        Games game7 = new Games("Forgetting the Microphone", "1:23:45");
        Games game8 = new Games("Extreme Monopoly", "1:23:45");
        Games[] games = new Games[]{game1, game2, game3, game4, game5, game6, game7, game8};
        return games;
    }

    public static class Games {
        SimpleStringProperty name, date;

        Games(String name, String date) {
            this.name = new SimpleStringProperty(name);;
            this.date = new SimpleStringProperty(date);;
        }

        public SimpleStringProperty getDateString() { return this.date; }
        public SimpleStringProperty getNameString() { return this.name; }
        public String getDate() { return this.date.get(); }
        public String getName() { return this.name.get(); }

    }
    public static HBox dateHbox(Button b, Text t){
        HBox h = new HBox(20);
        h.getChildren().setAll(b, t);
        return h;
    }

    public static Text textMaker(String s){
        Text t = new Text(s);
        t.setFont(Font.font("Arial Regular", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        return t;
    }

    public static LinearGradient backgroundStyle() {
        return new LinearGradient(
                0, 1, 1, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web(
                        "#191919"
                )),
                new Stop(1, Color.web(
                        "#031d55"
                        , 0.5
                ))
        );
    }

    public static Background backgroundColor(){
        return new Background(
                new BackgroundFill(
                        Color.rgb(33, 37, 43), null, null
                )
        );
    }

    public static Background saveBackground(){
        return new Background(
                new BackgroundFill(
                        Color.rgb(55, 55, 60), null, null
                )
        );
    }


}