package mainMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import boardGrid.*;

import java.io.IOException;
import java.util.Objects;

public class controller {
    @FXML
    public void toPlay(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PlayGameFXML.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void toEdit(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("editormenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void closeProgram(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void toMainMenu(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainMenuFXML.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void toModeMenu(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("mode-menu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("pause.fxml")));
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
