package preGame;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class playerSelect {
    private static Integer number;
    private static ArrayList<String> nameList;

    public playerSelect(){
        number = 0;
        nameList = new ArrayList<>();
    }

    public static HBox dropDown(){
        ComboBox<Integer> cBox = new ComboBox<>();
        cBox.setEditable(true);
        cBox.getItems().addAll(1, 2, 3, 4, 5);

        cBox.setOnAction((event -> {
            Object n = cBox.getSelectionModel().getSelectedItem();

            if (n != null){
                Integer num = 0;
                try{
                    String nString = n.toString();
                    num = Integer.parseInt(nString);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }
                number = num;
                System.out.println("Player number: " + number.toString());
            }
            else{
                System.out.println("Loading Error: Cannot read from ComboBox");
            }
        }));
        return new HBox(cBox);
    }

    public static HBox makeScene(Stage primaryStage, ArrayList<ArrayList<StackPane>> boardTable){
        HBox dropDownMenu = dropDown();
        Button nextButton = Helpers.Helper.ButtonMaker("Next", null);

        nextButton.setOnAction((event -> {

            primaryStage.setScene(new Scene(inputName(primaryStage, boardTable)));
        }));

        HBox numSelect = new HBox(50, dropDownMenu, nextButton);
        numSelect.setMinHeight(300);
        return numSelect;
    }

    public static GridPane inputName(Stage primaryStage, ArrayList<ArrayList<StackPane>> boardTable){
        GridPane root = new GridPane();
        nameList = new ArrayList<>();

        Label numLabel = new Label("Player number: " + number);
        Label playerLabel = new Label("Please Type Players name here ");
        root.addRow(0, numLabel);
        root.addRow(1, playerLabel);

        ArrayList<TextField> inputList = new ArrayList<>();
        for (int i = 0; i < number; i++){
            inputList.add(new TextField());
        }

        for (int i = 0; i < number; i++){
            Label empty = new Label();
            root.addRow(2 + i,empty, inputList.get(i));
        }

        Button submitButton = new Button("Submit");

        submitButton.setOnAction((event -> {
            for (TextField textField : inputList) {
                nameList.add(textField.getText());
            }
            for (String s : nameList) {
                System.out.println(s);
            }
            primaryStage.setScene(boardGrid.gamePlayUI.makeScene(primaryStage, nameList, boardTable));
        }));

        root.addRow(root.getRowCount(), submitButton);

        return root;
    }

}
