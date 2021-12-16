package GameEditor.Controllers;

import Objects.*;
import gamePlay.mainMenu.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class GameEditorMainController {
    LocalStorage localStorage = LocalStorage.getInstance();

    //TODO
    /*
        Confirmation prompt of game being saved.
        Don't exit the scene until pressing the actual exit button.
        After save, can continue to edit. If saved, and editing, just re-edit in
        the database, rather than a new savedGame.

        Saves the game and returns to main menu
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

        //save new game
        Token newGame = new Token(gameName, null, (Board) localStorage.storage.get("board"));
        new JSONConverter(newGame, "db/" + gameName +".json").To_JSON();

        popup(event, "Game has been saved");
        exitToMainMenu(event);
    }

    //TODO modifying an existing game would open it up in localStorage, edit, and do the same thing
    //TODO initialize localStorage
    //exits to main menu and resets localStorage
    @FXML
    public void exitToMainMenu(Event event) throws IOException {
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainMenuFXML.fxml")));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

        //resets localStorage on exit
        LocalStorage.reset();
    }

    //TODO getting the objects to save in the JSON
    //takes all the objects from localStorage and stores them in the JSON
    public void storeGameObjects(String game) throws IOException {
        for (Map.Entry<String, Object> entry: localStorage.storage.entrySet()) {
//            Rule rule = new Rule();
//            rule.update_rng((RNG) entry.getValue());
//            Board boardTemp  = new Board();
//            boardTemp.add_rule(rule);
//
//            Token newgame = new Token();
//            newgame.update_gameboard(boardTemp);
//            JSONConverter savedGames = new JSONConverter(newgame, "test.json");
//            savedGames.To_JSON();
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