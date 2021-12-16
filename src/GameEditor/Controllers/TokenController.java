package GameEditor.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TokenController {


    //ignore for now - Card Stuff
    @FXML
    void displayNewDeck(ActionEvent event) {

    }

    //Movement Piece Stuff
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

    //displays popup for creating a new movement piece
    public void addMovementPiece(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/NewMovementPiece.fxml"));
        Stage customTokenEditor = new Stage();
        customTokenEditor.setTitle("New Movement Piece");
        customTokenEditor.setScene(new Scene(root));
        customTokenEditor.show();
    }

    public void saveMovementPiece(ActionEvent event) throws IOException {}


}
