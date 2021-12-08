package GameEditor.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TokenController {
    //max amount of custom tokens that can be made
    private final int MAX = 10;
    //keeps track of which token is currently selected in the token editor screen
    private int CURRENT_ID;

    //Token Editor Screen Data
    @FXML
    private ImageView image;
    @FXML
    private TextField nameField, quantity;
    @FXML
    private Pane valuePane;


    //Custom Token Data
    @FXML
    private Pane ct0, ct1, ct2, ct3, ct4, ct5, ct6, ct7, ct8, ct9;
    @FXML
    private Button ct_del0, ct_del1, ct_del2, ct_del3, ct_del4,
                    ct_del5, ct_del6, ct_del7, ct_del8, ct_del9;
    @FXML
    private Button ct_edit0, ct_edit1, ct_edit2, ct_edit3, ct_edit4,
                    ct_edit5, ct_edit6, ct_edit7, ct_edit8, ct_edit9;
    @FXML
    private ImageView ct_image, ct_image1, ct_image2, ct_image3, ct_image4,
                    ct_image5, ct_image6, ct_image7, ct_image8, ct_image9;
    @FXML
    private Label ct_name0, ct_name1, ct_name2, ct_name3, ct_name4,
            ct_name5, ct_name6, ct_name7, ct_name8, ct_name9;


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


    //Methods for Custom Tokens
    @FXML
    void displayTokenEditor(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GameEditor/Views/NewCustomToken.fxml"));
            Stage customTokenEditor = new Stage();
            customTokenEditor.setTitle("Custom Token Editor");
            customTokenEditor.setScene(new Scene(root));
            customTokenEditor.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteToken(ActionEvent event) {

    }


    //Methods for Token Editor Screen
    @FXML
    void chooseImage(ActionEvent event) {

    }

    @FXML
    void enableValue(ActionEvent event) {

    }

    @FXML
    void saveToken(ActionEvent event) {

    }

    @FXML
    void cancelToken(ActionEvent event) {

    }

    //ignore for now
    @FXML
    void displayNewDeck(ActionEvent event) {

    }
}
