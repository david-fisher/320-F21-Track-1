package gamePlay.boardGrid;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class boardScene {

    public static Scene makeScene(Stage stage){
        int[] size = {8, 7};
        ArrayList<ArrayList<StackPane>> stackTable = new ArrayList<>();
        for (int i = 0; i < size[0]; i++){
            ArrayList<StackPane> row = new ArrayList<>();
            for (int j = 0; j < size[1]; j++){
                row.add(null);
            }
            stackTable.add(row);
        }

        ArrayList<String> playerList = new ArrayList<>();
        playerList.add(null);
        playerList.add("Fisher");
        playerList.add("Marius");

        Scene scene = gamePlayUI.makeScene(stage, playerList, stackTable);
        return scene;
    }
}
