import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class controller {
        @FXML
        public void toPlay(javafx.event.ActionEvent actionEvent) throws IOException {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PLayGameFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

        @FXML
        public void toEdit(javafx.event.ActionEvent event) throws IOException {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("editormenu.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainMenuFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    @FXML
    public void toGameplay(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gameplay.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    public void toPause(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pause.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    }
