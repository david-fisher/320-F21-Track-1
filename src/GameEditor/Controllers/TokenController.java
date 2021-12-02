package GameEditor.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TokenController {
    public void changeTokenMenu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        String tokenType = (String) node.getUserData();
        Stage app_stage = (Stage) node.getScene().getWindow();

        if (tokenType.equals("movementPiece")) {
            Parent addMovementPiece = FXMLLoader.load(getClass().getResource("TokenUI/MovementPiece.fxml"));
            app_stage.setScene(new Scene(addMovementPiece));
        }
        else if (tokenType == ""){}
        else {}
    }

    public void addMovementPiece(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        ScrollPane pane = (ScrollPane) node.getParent().getChildrenUnmodifiable().get(1);
        pane.setContent(FXMLLoader.load(getClass().getResource("../Views/NewMovementPiece.fxml")));
    }

    public void saveMovementPiece(ActionEvent event) throws IOException {

    }

    public  void displayNewDeck(){}

    public  void displayNewToken(){}

    public void enableValue(){}
}
