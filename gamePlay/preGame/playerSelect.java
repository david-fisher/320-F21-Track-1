package preGame;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class playerSelect {
    private static Integer number;  // total number of players
    private static ArrayList<String> nameList;    // save a list of play names
    private static ArrayList<Boolean> AIList;
    private static Boolean tutorial;
    private static int maxSize = 5;     // a preset bound index
    private static final double inputRootWidth = 900; private static final double inputRootHeight = 450;
    private static Label warning;
    private static boolean forward = false;

    public playerSelect(){
        number = 0;
        nameList = new ArrayList<>();
        AIList = new ArrayList<Boolean>();
        tutorial = false;
    }

    public boolean isTutorial() { return tutorial; }

    // update max player number
    public static void setMaxPlayer(int num){
        maxSize = num;
    }

    // return the dropDown menu
    private static VBox dropDown(){
        warning = new Label("Maximum player size is " + maxSize);
        warning.setId("pre_game_label");
        warning.setVisible(false);

        ComboBox<Integer> cBox = new ComboBox<>();
        cBox.setEditable(true);
        cBox.getItems().addAll(1, 2, 3, 4, 5);
        cBox.setMaxSize(150, 50);


        // when drop down menu is clicked do:
        cBox.setOnAction((event -> {
            Object n = cBox.getSelectionModel().getSelectedItem();  // might be user input

            if (n != null){
                int num = 1;
                try{
                    String nString = n.toString();
                    int currentNum = Integer.parseInt(nString);   // string input into integer
                    if (currentNum > 0){
                        num = currentNum;
                    }
                }
                catch (Exception e){
                    forward = false;
                    warning.setText("Please enter an integer");
                    warning.setVisible(true);
                    System.out.println(e.toString());
                    return;
                }
                if (num > maxSize){
                    forward = false;
                    warning.setText("Maximum player size is " + maxSize);
                    warning.setVisible(true);   // show warningL: over the maximum number
                    return;
                }

                number = num;
                warning.setVisible(false);
                forward = true;
                System.out.println("Player number: " + number.toString());
            }
            else{
                System.out.println("Loading Error: Cannot read from ComboBox");
            }
        }));
        VBox dropDownMenu = new VBox(cBox, warning);
        dropDownMenu.setAlignment(Pos.CENTER);
        return dropDownMenu;
    }

    // return the player number input box
    private static ScrollPane inputName(Stage primaryStage, ArrayList<ArrayList<StackPane>> boardTable){
        GridPane root = new GridPane();
        nameList = new ArrayList<>();
        AIList = new ArrayList<>();

        Label numLabel = new Label("Player number: " + number);
        Label playerLabel = new Label("Please Type Players name here ");
        Label AILabel = new Label("If player is AI");
        numLabel.setId("pre_game_label");
        playerLabel.setId("pre_game_label");
        AILabel.setId("pre_game_label");

        root.addRow(0, numLabel);
        root.addRow(1, playerLabel, new Label(), AILabel);

        // a list of input text field
        ArrayList<TextField> inputList = new ArrayList<>();
        for (int i = 0; i < number; i++){
            inputList.add(new TextField());
        }

        // a list of check boxes
        ArrayList<selectSwitch> switches = new ArrayList<>();
        for (int i = 0; i < number; i++){
            selectSwitch currentBox = new selectSwitch("AI");
            switches.add(currentBox);
        }

        for (int i = 0; i < number; i++){
            Label label = new Label("Player" + String.valueOf(i + 1) + ":");
            label.setId("pre_game_text");
            root.addRow(2 + i,label, inputList.get(i), switches.get(i));
        }

        Button backButton = new Button("Back");
        backButton.setId("input_back_button");

        // when user click <back>
        backButton.setOnAction((event -> {
            playerSelect.setMaxPlayer(6);
            Scene s = new Scene(makeScene(primaryStage, boardTable));
            s.getStylesheets().add("boardGrid/style.css");

            primaryStage.setX( primaryStage.getX());
            primaryStage.setY( primaryStage.getY());
            primaryStage.setScene(s);
        }));

        Button submitButton = new Button("Play");
        submitButton.setId("input_play_button");

        // when user click <play>
        submitButton.setOnAction((event -> {

            warning.setVisible(false);

            for (int i = 0; i < inputList.size(); i++){
                String currentName = inputList.get(i).getText();
                nameList.add(currentName.isEmpty()? null : currentName);
                boolean a = switches.get(i).switchState();
                AIList.add(switches.get(i).switchState());
            }

            for (int i = 0; i < inputList.size(); i++) {     // test printing call
                System.out.println(inputList.get(i).getText());
                System.out.print(AIList.get(i)? " I want AI" : " No AI");
            }

            primaryStage.centerOnScreen();
            primaryStage.setScene(boardGrid.gamePlayUI.makeScene(primaryStage, nameList, boardTable));
        }));

        root.addRow(root.getRowCount(), backButton, new Label(), submitButton);
        root.setAlignment(Pos.CENTER);
        root.setVgap(5);
        root.setHgap(10);
        root.setPrefSize(inputRootWidth, inputRootHeight);
        root.setBackground(Helpers.Helper.backgroundColor());

        ScrollPane scrollShow = new ScrollPane(root);
        scrollShow.setFitToHeight(true); scrollShow.setFitToWidth(true);

        return scrollShow;
    }

    // scene: init the player number dropdown menu -> input player names
    public static ScrollPane makeScene(Stage primaryStage, ArrayList<ArrayList<StackPane>> boardTable){
        tutorial = false;

        selectSwitch needTutorial = new selectSwitch("Tutorial");

        VBox dropDownMenu = dropDown();

        Button nextButton = new Button("Next");
        Button backButton = new Button("Back");
        nextButton.setId("next_button");
        backButton.setId("back_button");

        // forwarding: after number was selected
        nextButton.setOnAction((event -> {

            if (!forward) {     // over the maximum limit
                warning.setVisible(true);
                return;
            }

            tutorial = needTutorial.switchState();   // update if tutorial mode is selected

            Scene newScene = new Scene(inputName(primaryStage, boardTable));
            newScene.getStylesheets().add("boardGrid/style.css");

            primaryStage.setX( primaryStage.getX());
            primaryStage.setY( primaryStage.getY());
            primaryStage.setScene(newScene);

        }));

        // back to previous page
        backButton.setOnAction((event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(mainMenu.Main.class.getResource("PlayGameFXML.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }));

        HBox buttonBox = new HBox(30, backButton, nextButton);
        VBox tutorialWithButton = new VBox(30);
        tutorialWithButton.getChildren().addAll(needTutorial, buttonBox);
        tutorialWithButton.setAlignment(Pos.CENTER);

        HBox numSelect = new HBox(80, dropDownMenu, tutorialWithButton);
        numSelect.setAlignment(Pos.CENTER);
        numSelect.setPrefSize(inputRootWidth, inputRootHeight);
        numSelect.setBackground(
                Helpers.Helper.backgroundColor()
        );

        ScrollPane scrollShow = new ScrollPane(numSelect);
        scrollShow.setFitToHeight(true); scrollShow.setFitToWidth(true);
        return scrollShow;
    }

}
