package preGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class playerSelect {
    private static Integer number;  // total number of players
    private static ArrayList<String> nameList;    // save a list of play names
    private static ArrayList<Boolean> AIList;
    private static int maxSize = 5;     // a preset bound index

    public playerSelect(){
        number = 0;
        nameList = new ArrayList<>();
        AIList = new ArrayList<Boolean>();
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
        AIList = new ArrayList<>();

        Label numLabel = new Label("Player number: " + number);
        Label playerLabel = new Label("Please Type Players name here ");
        Label AILabel = new Label("If player is AI");
        root.addRow(0, numLabel);
        root.addRow(1, playerLabel, new Label(), AILabel);

        // a list of input text field
        ArrayList<TextField> inputList = new ArrayList<>();
        for (int i = 0; i < number; i++){
            inputList.add(new TextField());
        }

        // a list of check boxes
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        for (int i = 0; i < number; i++){
            checkBoxes.add(new CheckBox("AI"));
        }

        for (int i = 0; i < number; i++){
            Label label = new Label("Player" + String.valueOf(i + 1) + ":");
            root.addRow(2 + i,label, inputList.get(i), checkBoxes.get(i));
        }

        Button backButton = new Button("Back");

        // when user click <back>
        backButton.setOnAction((event -> {
            primaryStage.setScene(new Scene(makeScene(primaryStage, boardTable)));
        }));

        Button submitButton = new Button("Play");

        // when user click <play>
        submitButton.setOnAction((event -> {
            for (int i = 0; i < inputList.size(); i++){
                String currentName = inputList.get(i).getText();
                nameList.add(currentName.isEmpty()? null : currentName);
                boolean a = checkBoxes.get(i).isSelected();
                AIList.add(checkBoxes.get(i).isSelected());
            }

            for (int i = 0; i < inputList.size(); i++) {     // test printing call
                System.out.println(inputList.get(i).getText());
                System.out.print(AIList.get(i)? " I want AI" : " No AI");
            }
            primaryStage.setScene(boardGrid.gamePlayUI.makeScene(primaryStage, nameList, boardTable));
        }));

        root.addRow(root.getRowCount(), backButton, new Label(), submitButton);
        root.setAlignment(Pos.CENTER);
        root.setVgap(5);
        root.setHgap(10);
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
