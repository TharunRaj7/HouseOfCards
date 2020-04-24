package ooga.Controller;

import ooga.Model.Cards.Playable;
import ooga.Model.Games.*;
import ooga.View.GameScreens.GameScreen;
import org.json.simple.parser.ParseException;
//import ooga.View.Game;
//import ooga.View.GameScreens.GameScreen;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
//TODO: deal with makePlayer
public class GameController {
    private static final List<String> DEFAULT_GAMES = List.of(
            SolitaireDriver.class.getName(), HumanityDriver.class.getName(),
            MemoryDriver.class.getName(), GOPDriver.class.getName());
    GameDriver currentGame;
    List<String>playerNames;
    GameScreen currentScreen;

    public GameController(){
        playerNames = new ArrayList<>();
    }

    public GameScreen getGameScreen(String gameName, List<String> playerNames){
        Map<String, Object> ret = readJSON("gameScreen", gameName.toLowerCase());
        String gameScreenClass = (String) ret.get("className");
        GameScreen gameScreen = null;
        this.playerNames = playerNames;
        try {
            Constructor<?> constructor = Class.forName(gameScreenClass).getDeclaredConstructors()[0];
            gameScreen =(GameScreen) constructor.newInstance((GameController) this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return gameScreen;
    }
//TODO: Refactor this to read from data files.
    public Map<String, Object> initializeGame(GameTypes type){
        int index;
        switch (type){
            case SOLITAIRE:
                index = 0; break;
            case HUMANITY:
                index = 1; break;
            case MEMORY:
                index = 2; break;
            case GOP:
                index = 3; break;
            default:
                index = 0; break;
        }

        try {
            Constructor<?> constructor = Class.forName(DEFAULT_GAMES.get(index)).getDeclaredConstructors()[0];
            currentGame =(GameDriver) constructor.newInstance(this, playerNames);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //use reflections to make an instance of the appropriate game class and assign to currentGame
        Map<String, Object> ret = readJSON("view", type.toString().toLowerCase().strip());
        return ret;
    }


    private Map<String, Object> readJSON(String dataRequestedFor, String gameType){
        String path = "data/gameFiles/" + gameType + ".json";
        Map<String, Object> ret = null;
        try {
            ret = JSONReader.getData(dataRequestedFor, path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Object> updateProtocol(List<Object> args){
        List<Object> ret = currentGame.updateProtocol(args);
        return ret;
    } //return a list when win condition is met. Score is updated


    public Map<Integer, List<Integer>> requestCards (){
        //for solitaire this is a Map<Integer, List<Integer>>
        return currentGame.sendCards();
    }
    public String getImagePath(int cardID) {
        return currentGame.getCardImagePath(cardID);
    }
    public boolean isFaceUp(int cardID){
        return currentGame.IsCardFaceUp(cardID);
    }
    public void setToFaceUp(int cardID){
        currentGame.setFaceUp(cardID);
    }
    //TODO: getPlayerNames, getPlayerScores

    public List<String>getPlayerNames(){
        return currentGame.getPlayerNames();
    }

    public List<Integer>getPlayerScores(){
        return currentGame.getPlayerScores();
    }

    public void makePlayers(List<String> playerName){
        this.playerNames = playerName;
    }
    public String getValue(int cardID){
        return currentGame.getCardValue(cardID);
    }
    public void pauseGame(){}
    public void resumeGame(){}

    public static void main(String[] args) {
        GameController test = new GameController();
//        test.getGameScreen("Solitaire");
        Map<String, Object> m = test.initializeGame(GameTypes.SOLITAIRE);
        for (String s : m.keySet()){
            System.out.println(s + ": ");
            System.out.println(m.get(s));
        }

    }
}
