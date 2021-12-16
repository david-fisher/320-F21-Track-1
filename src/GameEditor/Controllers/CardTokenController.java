package GameEditor.Controllers;

import Objects.Tile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CardTokenController {

    @FXML
    private Pane editorPane, cardEditorPane;

    @FXML
    private ListView deckList, cardList;

    @FXML
    private Button newDeckButton, editButton, deleteButton, saveAllButton,
                    saveDeckButton, cancelDeckButton, newCardButton, editCardButton, deleteCardButton;
    @FXML
    private TextField nameField,  takePntField, givePntField;

    @FXML
    private CheckBox rollAgainCheckBox, givePointsCheckBox, takePointsCheckBox;

    @FXML
    private ChoiceBox<Tile> tileDropDown;
    @FXML
    private ChoiceBox<String> colorDropDown;

    //Other vars
    private final int MAX = 4; //max amount of decks that can be made
    private int numDecks;      //current number of decks made

    //Decks
    private ObservableList<String> deckNames = FXCollections.observableArrayList();
    private ArrayList<TempDeck> deckStorage;
    //Cards
    private ArrayList<TempCard> c1, c2, c3, c4;
    private ObservableList<String> cardNames1 = FXCollections.observableArrayList();
    private ObservableList<String> cardNames2 = FXCollections.observableArrayList();
    private ObservableList<String> cardNames3 = FXCollections.observableArrayList();
    private ObservableList<String> cardNames4 = FXCollections.observableArrayList();
    private ArrayList<ArrayList<TempCard>> cardStorage;
    private ArrayList<ObservableList<String>> namesStorage;
    private int[] cardAmt = {0,0,0,0};

    private boolean isEditingDeck;  //determines if we are currently editing a Deck
    private boolean isEditingCard;  //determines if we are currently editing a Deck

    @FXML
    void initialize() {
        initializeDecksAndCards();
    }

    void initializeDecksAndCards() {
        //deck & card storage
        deckStorage = new ArrayList<TempDeck>();
        c1 = new ArrayList<TempCard>();
        c2 = new ArrayList<TempCard>();
        c3 = new ArrayList<TempCard>();
        c4 = new ArrayList<TempCard>();
        cardStorage = new ArrayList<ArrayList<TempCard>>();
        cardStorage.add(c1);
        cardStorage.add(c2);
        cardStorage.add(c3);
        cardStorage.add(c4);
        namesStorage = new ArrayList<ObservableList<String>>();
        namesStorage.add(cardNames1);
        namesStorage.add(cardNames2);
        namesStorage.add(cardNames3);
        namesStorage.add(cardNames4);

        //other
        deckList.setItems(deckNames);
        cardList.setItems(cardNames1);
        numDecks = 0;
        isEditingDeck = false;
        isEditingCard = false;
        colorDropDown.setItems(FXCollections.observableArrayList("", "Red", "Green", "Blue", "Yellow"));
    }

    @FXML   //This is intended to save ALL decks created
    void saveAllDecks() {
    }

    //Deck Editor
    @FXML
    void toggleEditor(ActionEvent e) {

        //Making a NEW deck
        if (e.getSource() == newDeckButton) {
            if (numDecks < MAX) {                  //this prevents adding more decks that the limit allows
                editorPane.setVisible(true);
                nameField.setText("Deck " + (numDecks + 1));

            }
        }

        //Editing EXISTING deck
        if (e.getSource() == editButton) {
            //Find index of token we are editing
            int editIdx = deckList.getSelectionModel().getSelectedIndex();

            if (editIdx != -1) {
                TempDeck deckToEdit = null;

                for (int i = 0; i < deckStorage.size(); i++) {
                    if ((deckStorage.get(i).getId() - 1) == editIdx) {
                        deckToEdit = deckStorage.get(i);
                    }
                }

                editorPane.setVisible(true);
                cardList.setItems(namesStorage.get(editIdx));
                nameField.setText(deckToEdit.getName());
                colorDropDown.setValue(deckToEdit.getVisuals());

                isEditingDeck = true;
            }
        }
    }

    @FXML
    void saveDeck() {
        /* Save edits to EXISTING deck */
        if (isEditingDeck) {
            int editIdx = deckList.getSelectionModel().getSelectedIndex();
            deckNames.set(editIdx, nameField.getText());

            TempDeck deckToEdit = null;
            for (int i = 0; i < deckStorage.size(); i++) {
                if ((deckStorage.get(i).getId() - 1) == editIdx) {
                    deckToEdit = deckStorage.get(i);
                }
            }

            //save edits to the temp deck
            deckToEdit.setName(nameField.getText());
            editorPane.setVisible(false);
            cardEditorPane.setVisible(false);
            colorDropDown.setValue("");         //FIX?

            isEditingDeck = false;
        }
        /* Save NEW deck */
        else {
            numDecks++;
            int id = numDecks;
            deckNames.add(nameField.getText());     //add to list view

            TempDeck d = new TempDeck(id, nameField.getText(), colorDropDown.getValue(), cardStorage.get(id--));
            deckStorage.add(d);
            editorPane.setVisible(false);
            nameField.clear();

        }
    }

    @FXML
    void cancelDeck() {
        if (isEditingDeck) {
            isEditingDeck = false;
        }
        editorPane.setVisible(false);
        nameField.clear();
        //do dropdown
    }

    @FXML
    void setColorVisuals(ActionEvent e) {
    }

    @FXML
    void deleteDeck() {
        if (numDecks > 0) {
            numDecks--;
            int deleteIdx = deckList.getSelectionModel().getSelectedIndex();

            cardStorage.get(deleteIdx).clear(); //clear cards of deck
            deckList.getItems().remove(deleteIdx);
            deckStorage.remove(deleteIdx);
        }
    }


    //Card Editor
    @FXML
    void toggleCardEditor(ActionEvent e) {
        cardEditorPane.setVisible(true);

        //Making a NEW card
        if (e.getSource() == newCardButton) {
            //Placeholder; nothing happens at the moment
        }

        //Editing EXISTING card
        if (e.getSource() == editCardButton) {
            int deckIdx = 0;
            if (isEditingDeck) { deckIdx = deckList.getSelectionModel().getSelectedIndex(); }   //if editing a deck right now
            else if ( numDecks == 0 ) { deckIdx = 0; }
            else { deckIdx = numDecks--; }

            //Find index of card we are editing
            int editIdx = cardList.getSelectionModel().getSelectedIndex();

            if (editIdx != -1) {
                TempCard cardToEdit = null;
                /*
                for (int i = 0; i < cardStorage.size(); i++) {
                    if ((cardStorage.get(deckIdx).get(editIdx) - 1) == editIdx) {
                        deckToEdit = deckStorage.get(i);
                    }
                }
                **/

                cardToEdit = cardStorage.get(deckIdx).get(editIdx);

                cardList.setItems(namesStorage.get(deckIdx));
                if (cardToEdit.isRollAgain()) {
                    rollAgainCheckBox.setSelected(true);
                }
                if (cardToEdit.isGivePoints()) {
                    givePointsCheckBox.setSelected(true);
                    givePntField.setText(Integer.toString(cardToEdit.getPointsToGive()));
                }
                if (cardToEdit.isTakePoints()) {
                    takePointsCheckBox.setSelected(true);
                    takePntField.setText(Integer.toString(cardToEdit.getPointsToTake()));
                }


                isEditingDeck = true;
            }
        }
    }

    @FXML
    void saveCard() {
        /* Save edits to EXISTING card */
        if (isEditingCard) {
            int deckIdx = 0;
            int temp = deckList.getSelectionModel().getSelectedIndex();
            if (isEditingDeck && temp > -1) { deckIdx = deckList.getSelectionModel().getSelectedIndex(); }   //if editing a deck right now
            else { deckIdx = numDecks-1; }

            int editIdx = cardList.getSelectionModel().getSelectedIndex();
            namesStorage.get(deckIdx).set(editIdx, nameField.getText());

            TempCard cardToEdit = cardStorage.get(deckIdx).get(editIdx);

            //save edits to the temp card
            if (rollAgainCheckBox.isSelected()) { cardToEdit.setRollAgain(true); }
            else { cardToEdit.setRollAgain(false); }

            if (givePointsCheckBox.isSelected()) {
                cardToEdit.setGivePoints(true);
                cardToEdit.setPointsToGive(Integer.parseInt(givePntField.getText()));
            }
            else { cardToEdit.setGivePoints(true); }

            if (takePointsCheckBox.isSelected()) {
                cardToEdit.setTakePoints(true);
                cardToEdit.setPointsToTake(Integer.parseInt(takePntField.getText()));
            }
            else { cardToEdit.setTakePoints(true); }

            cardEditorPane.setVisible(false);

            isEditingCard = false;
        }
        /* Save NEW card */
        else {
            int deckIdx = 0;
            if (isEditingDeck) { deckIdx = deckList.getSelectionModel().getSelectedIndex(); }   //if editing a deck right now
            else if ( numDecks == 0 ) { deckIdx = 0; }
            else { deckIdx = numDecks--; }

            cardAmt[deckIdx]++;
            namesStorage.get(deckIdx).add("Card " + cardAmt[deckIdx]);

            TempCard c = new TempCard();
            cardStorage.get(deckIdx).add(c);
            cardEditorPane.setVisible(false);
            rollAgainCheckBox.setSelected(false);
            givePointsCheckBox.setSelected(false);
            givePntField.clear();
            takePointsCheckBox.setSelected(false);
            takePntField.clear();

        }
    }

    @FXML
    void deleteCard() {
        if (cardList.getSelectionModel().getSelectedIndex() != -1) {
            int deckIdx = 0;
            if (isEditingDeck) {
                deckIdx = deckList.getSelectionModel().getSelectedIndex();
            }   //if editing a deck right now
            else if (numDecks == 0) {
                deckIdx = 0;
            } else {
                deckIdx = numDecks--;
            }

            int deleteIdx = cardList.getSelectionModel().getSelectedIndex();

            cardStorage.get(deckIdx).remove(deleteIdx); //clear cards of deck
            cardList.getItems().remove(deleteIdx);
            namesStorage.get(deckIdx).remove(deleteIdx);
            cardEditorPane.setVisible(false);
        }
    }
}

class TempDeck {

    int id;
    String name;
    String visuals;
    ArrayList<TempCard> cards;

    //A deck has an id, a name, visuals, and an arraylist of TempCard objects
    TempDeck(int id, String name, String visuals, ArrayList<TempCard> cards) {
        this.id = id;
        this.name = name;
        this.visuals = visuals;
        this.cards = cards;
    }

    public void setName(String n) {
        name = n;
    }

    public void setVisuals(String v) {
        visuals = v;
    }

    public void setCards(ArrayList<TempCard> c) {
        cards = c;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getVisuals() { return visuals; }

    public ArrayList<TempCard> getCards() { return cards; }
}

class TempCard {

    //A card has 4 possible rules
    boolean rollAgain;
    boolean givePoints;
    boolean takePoints;
    boolean moveTo;

    int pointsToGive;
    int pointsToTake;
    Tile tileToMove;

    TempCard() {
        rollAgain = false;
        givePoints = false;
        takePoints = false;
        moveTo = false;
        pointsToGive = -1;
        pointsToTake = 1;
        tileToMove = null;
    }

    public void setRollAgain(boolean b) {
        rollAgain = b;
    }

    public void setGivePoints(boolean b) {
        givePoints = b;
    }

    public void setTakePoints(boolean b) {
        takePoints = b;
    }

    public void setMoveTo(boolean b) {
        moveTo = b;
    }

    public void setPointsToGive(int points) {
        pointsToGive = points;
    }

    public void setPointsToTake(int points) {
        pointsToTake = points;
    }

    public void setTileToMove(Tile tile) {
        tileToMove = tile;
    }

    public boolean isRollAgain() {
        return rollAgain;
    }

    public boolean isGivePoints() {
        return givePoints;
    }

    public boolean isTakePoints() {
        return takePoints;
    }

    public boolean isMoveTo() {
        return moveTo;
    }

    public int getPointsToGive() {
        return pointsToGive;
    }

    public int getPointsToTake(){
        return pointsToTake;
    }

    public  Tile getTileToMove() {
        return tileToMove;
    }

}
