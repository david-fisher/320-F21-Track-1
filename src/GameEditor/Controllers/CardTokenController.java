package GameEditor.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CardTokenController {

    @FXML
    private Button newDeckButton, editButton, deleteButton, saveAllButton,
                    saveDeckButton, cancelDeckButton, newCardButton, editCardButton, deleteCardButton;
    @FXML
    private ListView deckList, cardList;

    @FXML
    void initialize() {
    }

    @FXML
    void saveAllDecks() {
    }

    //Deck Editor
    @FXML
    void toggleEditor(ActionEvent e) {
    }

    @FXML
    void deleteDeck() {
    }

    @FXML
    void saveDeck() {
    }

    @FXML
    void cancelDeck() {
    }

    //Card Editor
    @FXML
    void toggleCardEditor(ActionEvent e) {
    }

    @FXML
    void deleteCard() {
    }
}
