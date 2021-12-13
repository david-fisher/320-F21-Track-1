package GameEditor.Controllers;

import GameEditor.DeckIds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TokenController {
    private DeckIds ids = DeckIds.getInstance();
    //Token Editor Screen Data
    @FXML
    private Button saveButton, cancelButton;
    @FXML
    private ImageView image;
    @FXML
    private TextField nameField, quantity, valueBox;
    @FXML
    private Pane valuePane;
    @FXML
    private CheckBox isPlayMoney, isMiscToken;

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

    //Other vars
    private final int MAX = 10; //max amount of custom tokens that can be made
    private int CURRENT_ID;     //keeps track of which token is currently selected in the token editor screen

    private Pane[] Tokens;
    private Label[] Names;
    private ImageView[] Images;
    private Button[] Deletes;
    private int numVisible = 0;
    private int[] visible;

    @FXML
    void initialize() {
        initializeCustomTokens();
    }

    void initializeCustomTokens() {
        Tokens = new Pane[MAX];
        Tokens[0] = ct0; Tokens[1] = ct1; Tokens[2] = ct2; Tokens[3] = ct3; Tokens[4] = ct4;
        Tokens[5] = ct5; Tokens[6] = ct6; Tokens[7] = ct7; Tokens[8] = ct8; Tokens[9] = ct9;

        Names = new Label[MAX];
        Names[0] = ct_name0; Names[1] = ct_name1; Names[2] = ct_name2; Names[3] = ct_name3; Names[4] = ct_name4;
        Names[5] = ct_name5; Names[6] = ct_name6; Names[7] = ct_name7; Names[8] = ct_name8; Names[9] = ct_name9;

        Images = new ImageView[MAX];
        Images[0] = ct_image; Images[1] = ct_image1; Images[2] = ct_image2; Images[3] = ct_image3; Images[4] = ct_image4;
        Images[5] = ct_image5; Images[6] = ct_image6; Images[7] = ct_image7; Images[8] = ct_image8; Images[9] = ct_image9;

        Deletes = new Button[MAX];
        Deletes[0] = ct_del0; Deletes[1] = ct_del1; Deletes[2] = ct_del2; Deletes[3] = ct_del3; Deletes[4] = ct_del4;
        Deletes[5] = ct_del5; Deletes[6] = ct_del6; Deletes[7] = ct_del7; Deletes[8] = ct_del8; Deletes[9] = ct_del9;

        visible = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            visible[i] = 0;
        }
    }

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
    void setPlayMoney(ActionEvent event) {
        if (valuePane.isDisable()) {        //if "Play Money" box is checked
            valuePane.setDisable(false);
            isMiscToken.setDisable(true);
            isMiscToken.setSelected(false);
        }
        else {                              //if "Play Money" box is unchecked
            valuePane.setDisable(true);
            valueBox.clear();
            isMiscToken.setDisable(false);
        }
    }

    @FXML
    void setMiscToken(ActionEvent event) {
        if (isPlayMoney.isDisable()) {      //if "Misc. Token" box is unchecked
            isPlayMoney.setDisable(false);
        }
        else {
            isPlayMoney.setDisable(true);   //if "Misc. Token" box is checked
            isPlayMoney.setSelected(false);
            valueBox.clear();
        }
    }

    @FXML
    void saveToken(ActionEvent event) {
        if (numVisible >= MAX) {
            System.out.println("Max token limit reached");
        }
        else {
            int hidden;
            for (int i = 0; i < MAX; i++) {
                if (visible[i] == 0) {
                    Stage stage = (Stage) saveButton.getScene().getWindow();
                    stage.close();
                    hidden = i;
                    Tokens[hidden].setVisible(true);
                    //Names[hidden].setText();
                    numVisible++;
                    visible[i] = 1;
                    return;
                }
            }
        }
    }

    @FXML
    void cancelToken(ActionEvent event) {

    }

    //ignore for now
    @FXML
    void displayNewDeck(ActionEvent event) {

    }
}
