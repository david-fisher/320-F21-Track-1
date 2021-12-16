package GameEditor.Controllers;

import Objects.Board;
import Objects.RNG;
import Objects.JSONConverter;
import Objects.Token;
import gamePlay.mainMenu.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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

        //checks if required game objects are created
        if (localStorage.storage.isEmpty()) popup(event, "must create game objects");

        //save new game
        else {
            Token newGame = new Token(gameName, null, (Board) localStorage.storage.get("board"));
            ArrayList<RNG> rng = (ArrayList<RNG>) localStorage.storage.get("RNG");

            //TODO check if game already exists in database
            new JSONConverter(newGame, "db/" + gameName + ".json").To_JSON();

            popup(event, gameName + " has been saved");
            exitToMainMenu(event);
        }
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


    //creates a popup window
    @FXML
    public void popup(Event event, String argument) {
        Dialog<String> saved = new Dialog<String>();
        saved.getDialogPane().setContentText(argument);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        saved.getDialogPane().getButtonTypes().add(type);
        saved.showAndWait();
    }

}