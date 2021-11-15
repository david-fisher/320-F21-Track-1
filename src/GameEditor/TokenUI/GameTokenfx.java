import java.util.*;

abstract class TokenUI {
    Token currentToken;
    LinkedList<Token> tokenList; 

    abstract void save();
    abstract void cancel();
    abstract void delete();
    abstract void newDeck();
    abstract void newMovementPiece();
    abstract void newCustomPiece();
    public void newCard(Token deck, Token card) {
        deck.newCard(card);
    }
}
