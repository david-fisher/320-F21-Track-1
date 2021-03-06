package gamePlay.mainMenu;

import GameEditor.BoardEditor;
import GameEditor.GameEditorMain;
import gamePlay.preGame.preGame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import gamePlay.boardGrid.*;
import gamePlay.mainMenu.Main;
import gamePlay.mainMenu.saveGameViewController;

import java.io.IOException;
import java.util.Objects;

public class controller {
    @FXML
    public void toPlay(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(gamePlay.mainMenu.Main.class.getResource("PlayGameFXML.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    public void toEditorMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(gamePlay.mainMenu.Main.class.getResource("editormenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    public void toEdit(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(Objects.requireNonNull(gamePlay.mainMenu.Main.class.getResource("editormenu.fxml")));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        Parent menu = FXMLLoader.load(Objects.requireNonNull(GameEditorMain.class.getResource("Views/GameEditorMain.fxml")));
        Scene mainMenu = new Scene(menu);
        TabPane tabpane = (TabPane) menu.getChildrenUnmodifiable().get(0);
        tabpane.getTabs().get(0).setContent(new BoardEditor().startBoardEditor(stage));
        stage.setScene(mainMenu);
    }

    @FXML
    public void closeProgram(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void toMainMenu(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(gamePlay.mainMenu.Main.class.getResource("MainMenuFXML.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void toModeMenu(javafx.event.ActionEvent event) throws IOException {
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(Objects.requireNonNull(gamePlay.mainMenuMain.class.getResource("mode-menu.fxml")));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(preGame.makeScene(stage));
    }

    @FXML
    public void toGameplay(javafx.event.ActionEvent event) throws IOException {
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gameplay.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(boardScene.makeScene(stage));
    }

    @FXML
    public void toPause(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(gamePlay.mainMenu.Main.class.getResource("pause.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void toSavedGame(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = saveGameViewController.makeScene(stage);
        stage.setScene(scene);
    }

    @FXML
    public void closeWindow(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void exitApp(javafx.event.ActionEvent event) throws IOException {
        Platform.exit();
    }

    @FXML
    public void toBoard(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(boardScene.makeScene(stage));
    }

}
