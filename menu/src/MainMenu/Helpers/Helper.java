package MainMenu.Helpers;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Helper {
    public static Button ButtonMaker(String text){
        Button btn = new Button();
        btn.setText(text);
        btn.setPrefWidth(300);
        //btn.setPrefHeight(100);
        btn.setFont(Font.font("Arial Regular", FontWeight.BOLD, FontPosture.REGULAR, 30));
        return(btn);
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
        public String name;
        public String date;

        Games(String n, String d) {
            this.name = n;
            this.date = d;
        }
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

}
