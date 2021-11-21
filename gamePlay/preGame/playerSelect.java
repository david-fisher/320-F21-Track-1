package preGame;

import javafx.geometry.Pos;
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
    private static Integer number;  // total number of players
    private static ArrayList<String> nameList;    // save a list of play names
    private static int maxSize = 5;     // a preset bound index

    public playerSelect(){
        number = 0;
        nameList = new ArrayList<>();
    }

    // update max player number
    public static void setMaxPlayer(int num){
        maxSize = num;
    }

    // return the dropDown menu
    private static HBox dropDown(){
        Label warning = new Label("Maximum player size is " + maxSize);
        warning.setVisible(false);

        ComboBox<Integer> cBox = new ComboBox<>();
        cBox.setEditable(true);
        cBox.getItems().addAll(1, 2, 3, 4, 5);

        // when drop down menu is clicked do:
        cBox.setOnAction((event -> {
            Object n = cBox.getSelectionModel().getSelectedItem();  // might be user input

            if (n != null){
                int num = 0;
                try{
                    String nString = n.toString();
                    num = Integer.parseInt(nString);    // string input into integer
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }
                if (num > maxSize){
                    warning.setVisible(true);   // show warningL: over the maximum number
                    return;
                }

                number = num;
                System.out.println("Player number: " + number.toString());
            }
            else{
                System.out.println("Loading Error: Cannot read from ComboBox");
            }
        }));
        HBox dropDownMenu = new HBox(cBox, warning);
        dropDownMenu.setAlignment(Pos.CENTER);
        return dropDownMenu;
    }

    // return the player number input box
    private static GridPane inputName(Stage primaryStage, ArrayList<ArrayList<StackPane>> boardTable){
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
            Label label = new Label("Player" + String.valueOf(i + 1) + ":");
            root.addRow(2 + i,label, inputList.get(i));
        }

        Button backButton = new Button("Back");

        // when user click <back>
        backButton.setOnAction((event -> {
            primaryStage.setScene(new Scene(makeScene(primaryStage, boardTable)));
        }));

        Button submitButton = new Button("Play");

        // when user click <play>
        submitButton.setOnAction((event -> {
            for (TextField textField : inputList) {
                String currentName = textField.getText();
                nameList.add(currentName.isEmpty()? null : textField.getText());
            }
            for (String s : nameList) {     // test printing call
                System.out.println(s);
            }
            primaryStage.setScene(boardGrid.gamePlayUI.makeScene(primaryStage, nameList, boardTable));
        }));

        root.addRow(root.getRowCount(), backButton, submitButton);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(500, 300);

        return root;
    }

    // scene: init the player number dropdown menu -> input player names
    public static HBox makeScene(Stage primaryStage, ArrayList<ArrayList<StackPane>> boardTable){
        HBox dropDownMenu = dropDown();
        Button nextButton = Helpers.Helper.ButtonMaker("Next", null);

        // forwarding: after number was selected
        nextButton.setOnAction((event -> {

            primaryStage.setScene(new Scene(inputName(primaryStage, boardTable)));
        }));

        HBox numSelect = new HBox(50, dropDownMenu, nextButton);
        numSelect.setAlignment(Pos.CENTER);
        numSelect.setMinHeight(300);
        return numSelect;
    }

}
