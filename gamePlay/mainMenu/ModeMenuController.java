package src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeMenuController {
    @FXML
    private Spinner aiSpinner;

    @FXML
    private Spinner playerSpinner;

    private void initSpinner(int playerMax, int aiMax) {
        playerSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, playerMax));

        aiSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aiMax));

    }
    @FXML
    public void initialize() {
        initSpinner(10, 4);
    }

    @FXML
    public void toPlay(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PlayGameFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
