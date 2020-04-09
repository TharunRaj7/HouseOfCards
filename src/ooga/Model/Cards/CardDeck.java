package ooga.Model.Cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.Controller.CardColors;
import ooga.Controller.DeckType;
import ooga.Controller.GameTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck implements Deck {
    DeckType deckType;
    List<Playable> gameCards;

    public CardDeck (GameTypes type, CardColors color){
        switch (type){
            // need to split this to 2 different decks for the questions and answers
            case HUMANITY:
                deckType = DeckType.HUMANITY;
                break;
            case UNO:
                deckType = DeckType.UNO;
                break;
            default:
                deckType = DeckType.POKER;
        }
        gameCards = new ArrayList<>();
        CardFactory.initializeDeck(this, deckType, color);

    }

    // TODO: Might not need this
    public CardDeck (CardDeck cardDeck){
        this.deckType = cardDeck.getDeckType();
        this.gameCards = cardDeck.getCards();
    }

    public CardDeck(DeckType deckType){
        this.deckType = deckType;
        gameCards = new ArrayList<>();
    }

    @Override
    public void addCard(Playable card) {
        gameCards.add(card);
    }

    @Override
    public void makeCard(String cardType) {

    }

    public List<Playable> getCards(){
        return gameCards;
    }

    public DeckType getDeckType (){
        return deckType;
    }

    @Override
    public void shuffleDeck() {
        Collections.shuffle(gameCards);
    }

    @Override
    public String toString(){
        String ret = "";
        for (Playable card : gameCards){
            ret = ret + card.getNumber() + card.getValue() + "\n";
        }
        return ret;
    }
}
