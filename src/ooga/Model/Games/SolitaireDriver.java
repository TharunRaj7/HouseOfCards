package ooga.Model.Games;

import ooga.Controller.CardColors;
import ooga.Controller.DeckType;
import ooga.Controller.GameTypes;
import ooga.Model.Cards.CardDeck;
import ooga.Model.Cards.Deck;
import ooga.Model.Players.Player;

public class SolitaireDriver extends GameDriver{
    private CardColors DEFAULT_COLOR = CardColors.BLUE;
    private Player player;
    private CardDeck [] gameDecks;
    int score;
    //many decks since solitaire contains decks everywhere
    public SolitaireDriver (){
        makeDecks();
    }

    private void makeDecks(){
       gameDecks = new CardDeck[8];
       for (int i = 0; i < gameDecks.length; i++){
           gameDecks[i] = new CardDeck(GameTypes.SOLITAIRE, DEFAULT_COLOR);
       }
    }

    @Override
    public void makePlayer(String userName) {

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    /**
     * Function to check the winning condition
     */
    public boolean checkWin() {
        return false;
    }

    @Override
    public void updateProtocol() {

    }

    @Override
    public void updateScore(int score) {

    }

    @Override
    public void changeLevel(int level) {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void startGame() {

    }

    public static void main (String[]args){
        SolitaireDriver test = new SolitaireDriver();
        for (CardDeck deck : test.gameDecks){
            System.out.println(deck);
        }
    }
}
