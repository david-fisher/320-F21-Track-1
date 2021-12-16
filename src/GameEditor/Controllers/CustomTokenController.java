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
    private Button newCustTokenButton, editButton, deleteButton, saveButton, cancelButton, saveAllButton;
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
        valuePane.setDisable(true);
        miscCheckBox.setSelected(true);             //by default, all tokens are miscellaneous tokens
        playMoneyCheckBox.setDisable(true);
    }

    // IMPORTANT: This method saves all created tokens to the entire game
    @FXML
    void saveAllTokens() {

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
                }

                editorPane.setVisible(true);
                nameField.setText(tokenToEdit.getName());
                qtyField.setText(Integer.toString(tokenToEdit.getQty()));
                //image stuff here
                if (tokenToEdit.isPlayMoney()) {
                    playMoneyCheckBox.setSelected(true);
                    valuePane.setDisable(false);
                    valueField.setText(Integer.toString(tokenToEdit.getValue()));
                }
                if (tokenToEdit.isMiscToken()) {
                    miscCheckBox.setSelected(true);
                }

                isEditing = true;
            }
        }

    }

    @FXML
    void setPlayMoney() {
        if (playMoneyCheckBox.isSelected()) {
            valuePane.setDisable(false);
            miscCheckBox.setDisable(true);
        }
        else {
            valuePane.setDisable(true);
            valueField.clear();
            miscCheckBox.setDisable(false);
        }
    }

    @FXML
    void setMiscToken() {
        if (miscCheckBox.isSelected()) {
            valuePane.setDisable(true);
            playMoneyCheckBox.setDisable(true);
            valueField.clear();
        }
        else {
            playMoneyCheckBox.setDisable(false);
        }
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

            TempToken tokenToEdit = null;
            for (int i = 0; i < tokenStorage.size(); i++) {
                if ((tokenStorage.get(i).getId() - 1) == editIdx) {
                    tokenToEdit = tokenStorage.get(i);
                }
            }

            //save edits to the temp token
            tokenToEdit.setName(nameField.getText());
            tokenToEdit.setQty(Integer.parseInt(qtyField.getText()));
            if (playMoneyCheckBox.isSelected()) {
                tokenToEdit.setPlayMoney(true);
                tokenToEdit.setValue(Integer.parseInt(valueField.getText()));
            }
            else {
                tokenToEdit.setMiscToken(true);
            }

            editorPane.setVisible(false);
            nameField.clear();
            qtyField.clear();
            //image stuff
            playMoneyCheckBox.setSelected(false);
            valuePane.setDisable(true);
            valueField.clear();
            isEditing = false;
        }
        /* Save NEW token */
        else {
            numTokens++;
            names.add(nameField.getText());     //add to list view
            //TODO: assign visuals to new TempToken object

            int value = 0;
            if (valueField.getText().isEmpty()) {
                value = -1;
            }
            else {
                value = Integer.parseInt(valueField.getText());
            }

            TempToken t = new TempToken(numTokens, nameField.getText(), null, Integer.parseInt(qtyField.getText()),
                                        playMoneyCheckBox.isSelected(), value, miscCheckBox.isSelected());
            tokenStorage.add(t);

            editorPane.setVisible(false);
            nameField.clear();
            qtyField.clear();
            playMoneyCheckBox.setSelected(false);
            valuePane.setDisable(true);
            valueField.clear();
            miscCheckBox.setSelected(true);
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
        playMoneyCheckBox.setSelected(false);
        playMoneyCheckBox.setDisable(true);
        valuePane.setDisable(true);
        valueField.clear();
        miscCheckBox.setSelected(true);
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
    boolean playMoney;
    int value;
    boolean miscToken;


    TempToken(int id, String name, ImageView visuals, int qty, boolean playMoney, int value, boolean miscToken) {
        this.id = id;
        this.name = name;
        this.visuals = visuals;
        this.qty = qty;
        this.playMoney = playMoney;
        this.value = value;
        this.miscToken = miscToken;
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

    public void setPlayMoney(boolean isPlay) {
        playMoney = isPlay;
    }

    public void setValue(int val) {
        value = val;
    }

    public void setMiscToken(boolean isMisc) {
        miscToken = isMisc;
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

    public boolean isPlayMoney() {
        return playMoney;
    }

    public int getValue() {
        return value;
    }

    public boolean isMiscToken() {
        return miscToken;
    }
}

