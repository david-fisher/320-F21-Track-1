package GameEditor.Controllers;

import Objects.JSONConverter;
import Objects.Token;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mainMenu.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameEditorMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ComboBox<String> dropdown0, dropdown1, dropdown2, dropdown3, dropdown4;
    @FXML
    private Button transition;
    @FXML
    private Button addTransition;
    @FXML
    private HBox modifyTransitionButtons;
    @FXML
    private Button changeTransition;
    @FXML
    private Button deleteTransition;
    
    private String[] transitions = {"\u2192", "\u2190", "\u2194"};
    private int currentTransition = 0;
    
    @FXML
    private ComboBox<String>[] dropdowns = new ComboBox[5];
    private int numVisible = 1;
    private int[] visible = { 1, 0,0,0,0};
    private String[] deleteButtons = { "d0", "d1", "d2", "d3", "d4" };
    private double orgSceneX, orgSceneY;

    @FXML
    private URL location;

    @FXML
    void initializeTurnRule() {
        dropdowns[0] = dropdown0;
        dropdowns[1] = dropdown1;
        dropdowns[2] = dropdown2;
        dropdowns[3] = dropdown3;
        dropdowns[4] = dropdown4;
        dropdowns[1].getParent().setManaged(false);;
        dropdowns[2].getParent().setManaged(false);;
        dropdowns[3].getParent().setManaged(false);;
        dropdowns[4].getParent().setManaged(false);;
    }

    @FXML
    void addDropDown(MouseEvent event) {
        if (numVisible < 5) {
            int hidden;
            for (int i = 0; i < visible.length; i++) {
                if (visible[i] == 0) {
                    hidden = i;
                    dropdowns[hidden].getParent().setVisible(true);
                    dropdowns[hidden].getParent().setManaged(true);
                    numVisible++;
                    visible[i] = 1;
                    return;
                }
            }
        }
    }

    @FXML
    void deleteDropDown(MouseEvent event) {
        // make the appropriate nodes not visible and clear and selection for that dropdown
        ((Node) event.getSource()).getParent().setVisible(false);
        ((Node) event.getSource()).getParent().setManaged(false);
        ((ComboBox<String>) ((Node) event.getSource()).getParent().getChildrenUnmodifiable().get(0)).getSelectionModel()
                .clearSelection();
        numVisible--;
        for (int i = 0; i < deleteButtons.length; i++) {
            if ((((Node) event.getSource()).getId()).equals(deleteButtons[i])) {
                visible[i] = 0;
                return;
            }
        }
    }
    
    @FXML
    void intializeMovementRule(MouseEvent event) {
    	modifyTransitionButtons.setVisible(false);
    	addTransition.setVisible(true);
    }
    
    @FXML
    void selectTransition(MouseEvent event) {
    	addTransition.setVisible(false);
    	modifyTransitionButtons.setVisible(true);
    }
    
    @FXML
    void changeTransition(MouseEvent event) {
    	currentTransition = (currentTransition+1) % 3;
    	transition.setText(transitions[currentTransition]);
    }

    @FXML
    void deleteTransition(MouseEvent event) {
    	transition.setVisible(false);
    }

    @FXML
    public void changeAnchorScene(Event event) throws IOException {
        Node node = (Node) event.getSource();
        String tokenType = (String) node.getUserData();
        Stage app_stage = (Stage) node.getScene().getWindow();

        if (tokenType.equals("gameToken")) {
            Parent addMovementPiece = FXMLLoader.load(getClass().getResource("Views/customToken.fxml"));
            app_stage.setScene(new Scene(addMovementPiece));
        }
        else if (tokenType == ""){}
        else {}
    }

    //TODO
    /*
        Confirmation prompt of game being saved.
        Don't exit the scene until pressing the actual exit button.
        After save, can continue to edit. If saved, and editing, just re-edit in
        the database, rather than a new savedGame.
     */
    
    @FXML
    public void saveGame(Event event) throws IOException {
        Node node = (Node) event.getSource();
        //saveGame
        TextField text = (TextField) node.getParent().getChildrenUnmodifiable().get(0);
        String gameName = text.getText();
        if (gameName.equals("")) {
            gameName = "Game" + new Random().nextInt(10000);
        }
        Token newgame = new Token(gameName);
        JSONConverter savedGames = new JSONConverter(newgame, "test.json");
        savedGames.To_JSON();


        storeGameObjects();

        popup(event, "Game has been saved");
        exitToMainMenu(node);

    }

    public void exitToMainMenu(Node node) throws IOException {
        //change stage to mainMenu stage from gamePlay
        Stage appStage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainMenuFXML.fxml")));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    //TODO getting the objects to save in the JSON
    //takes all the objects from localStorage and stores them in the JSON
    public void storeGameObjects() {
        LocalStorage localStorage = LocalStorage.LocalStorage();
        for (Map.Entry<String, Object> entry: localStorage.storage.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    //creates a popup window
    @FXML
    public void popup(Event event, String argument) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(argument);
        stage.show();
    }

}