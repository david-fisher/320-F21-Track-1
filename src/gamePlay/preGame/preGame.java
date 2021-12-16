package gamePlay.preGame;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class preGame{

    public static Scene makeScene(Stage primaryStage){
        playerSelect.setMaxPlayer(6);
        Scene scene = new Scene(playerSelect.makeScene(primaryStage, generateTestCell()));
        scene.getStylesheets().add("gamePlay/boardGrid/style.css");
        return scene;
    }

    private static ArrayList<ArrayList<StackPane>> generateTestCell(){

        /*int[] size = {8, 7};
        ArrayList<ArrayList<StackPane>> stackTable = new ArrayList<>();
        for (int i = 0; i < size[0]; i++){
            ArrayList<StackPane> row = new ArrayList<>();
            for (int j = 0; j < size[1]; j++){
                row.add(null);
            }
            stackTable.add(row);
        }
        return stackTable;
        }*/
        //Comment out the below code and uncomment the above code to revert
        int[] size = {5, 5};
        ArrayList<ArrayList<StackPane>> stackTable = new ArrayList<>();
        for (int i = 0; i < size[0]; i++){
            ArrayList<StackPane> row = new ArrayList<>();
            for (int j = 0; j < size[1]; j++){
                if(i == 0 || i == size[0]-1 || j == 0 || j == size[1]-1){
                    StackPane stack = new StackPane();
                    Rectangle square = new Rectangle(80, 80);
                    square.setFill(i % 2 == 0 ? Color.BISQUE : Color.AQUA);
                    stack.getChildren().add(square);
                    row.add(stack);
                }
                else{
                    row.add(null);
                }
            }
            stackTable.add(row);
            }


        return stackTable;
    }


}
