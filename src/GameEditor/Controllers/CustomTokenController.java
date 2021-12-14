package GameEditor.Controllers;

import GameEditor.DeckIds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CustomTokenController {
    private DeckIds ids = DeckIds.getInstance();

    //Custom Token FXMLs
    @FXML
    private Pane editorPane, valuePane;
    @FXML
    private TextField nameField, qtyField, valueField;
    @FXML
    private ListView tokensList;
    @FXML
    private Button newCustTokenButton, editButton, deleteButton, saveButton, cancelButton;
    @FXML
    private CheckBox playMoneyCheckBox, miscCheckBox;

    //Other vars
    private final int MAX = 10; //max amount of custom tokens that can be made
    private int numTokens;      //current number of tokens made

    private ObservableList<String> names = FXCollections.observableArrayList();
    private ArrayList<TempToken> tokenStorage;
    private boolean isEditing;  //determines if we are currently editing a token

    @FXML
    public void initialize() {
        initializeCustomTokens();
    }

    //Methods for Custom Tokens
    void initializeCustomTokens() {
        tokensList.setItems(names);
        numTokens = 0;
        tokenStorage = new ArrayList<TempToken>();
        isEditing = false;
    }

    @FXML
    void toggleEditor(ActionEvent e) {
        /** Purpose:    Displays editor for either creating new token or editing existing
         * Called by:   newCustTokenButton, editButton
         */

        /* Creating NEW token */
        if (e.getSource() == newCustTokenButton) {
            if (numTokens < MAX) {                  //this prevents adding more tokens that the limit allows
                editorPane.setVisible(true);
                nameField.setText("Token " + (numTokens + 1));
                qtyField.setText("1");
                //image stuff
                //checkbox stuff
            }
        }

        /* Editing EXISTING token */
        if (e.getSource() == editButton) {
            //Find index of token we are editing
            int editIdx = tokensList.getSelectionModel().getSelectedIndex();

            if (editIdx != -1) {
                TempToken tokenToEdit = null;

                for (int i = 0; i < tokenStorage.size(); i++) {
                    if ((tokenStorage.get(i).getId() - 1) == editIdx) {
                        tokenToEdit = tokenStorage.get(i);
                    }

                    editorPane.setVisible(true);
                    nameField.setText(tokenToEdit.getName());
                    qtyField.setText(Integer.toString(tokenToEdit.getQty()));
                    //image stuff
                    //checkbox stuff
                    isEditing = true;
                }
            }
        }

    }

    @FXML
    void setPlayMoney() {

    }

    @FXML
    void setMiscToken() {

    }

    @FXML
    void saveToken() {
        /** Purpose:    Saves edits to EXISTING token or saves NEW token
         * Called by:   saveButton
         */

        /* Save edits to EXISTING token */
        if (isEditing) {
            int editIdx = tokensList.getSelectionModel().getSelectedIndex();

            names.set(editIdx, nameField.getText());
            editorPane.setVisible(false);
            nameField.clear();
            qtyField.clear();
            //image stuff
            //checkbox stuff
            isEditing = false;
        }
        /* Save NEW token */
        else {
            numTokens++;
            names.add(nameField.getText());     //add to list view
            //TODO: assign visuals to new TempToken object
            TempToken t = new TempToken(numTokens, nameField.getText(), null, Integer.parseInt(qtyField.getText()));
            tokenStorage.add(t);

            editorPane.setVisible(false);
            nameField.clear();
            qtyField.clear();
        }
    }

    @FXML
    void cancelToken() {
        /** Purpose:    Cancels the creation / editing of current token
         *  Called By:  cancelButton
         */

        if (isEditing) {
            isEditing = false;
        }
        editorPane.setVisible(false);
        nameField.clear();
        qtyField.clear();
        //image stuff
        //checkbox stuff
    }

    @FXML
    void deleteToken() {
        if (numTokens > 0) {
            numTokens--;
            int deleteIdx = tokensList.getSelectionModel().getSelectedIndex();

            tokensList.getItems().remove(deleteIdx);
            tokenStorage.remove(deleteIdx);
        }
    }

}

//A class for temporary storage of tokens; only meant to be used by the token editor
class TempToken {

    int id;
    String name;
    ImageView visuals;
    int qty;


    TempToken(int id, String name, ImageView visuals, int qty) {
        this.id = id;
        this.name = name;
        this.visuals = visuals;
        this.qty = qty;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setVisuals(ImageView newImage) {
        visuals = newImage;
    }

    public void setQty(int newQty) {
        qty = newQty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageView getVisuals() {
        return visuals;
    }

    public int getQty() {
        return qty;
    }
}

