package ooga.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ooga.Controller.GameController;
import ooga.View.GameScreens.CAHScreen;
import ooga.View.GameScreens.GameScreen;
import ooga.View.GameScreens.SolitaireScreen;
import ooga.View.utils.WinScreen;

import java.util.List;
import java.util.Objects;

public class UserInterface extends Application implements Viewable {
    private double VIEW_WIDTH = 1200;
    private double VIEW_HEIGHT = 650;
    private Stage myStage;
    private static GameController passingController;
    private Scene splash;

    @Override
    public void start(Stage primaryStage) {
        passingController = new GameController();
        myStage = primaryStage;
        SplashScreen splashScreen = new SplashScreen(this);
        Scene disp = splashScreen.getStartScene();
        splash = disp;
        myStage.setScene(disp);
        myStage.show();
    }

    @Override
    public void moveCard(int id, int x, int y) {

    }

    @Override
    public void updateScore(int score) {

    }

    @Override
    public void displayCard(Image cardView) {

    }

    @Override
    public void makeDeck(Deck deck) {

    }

    @Override
    public void downloadGame(Game game) {

    }

    @Override
    public void gameMessage(String message) {

    }

    @Override
    public void getNewCard(Deck deck) {

    }

    @Override
    public void shuffleDeck(int deckId) {

    }

    @Override
    public void generateGame(int gameChoice) {

    }

    @Override
    public void endGame() {

    }

    @Override
    public Deck getDeck(int x, int y) {
        return null;
    }

    public void userScreen(String gameName){
        UserInput c= new UserInput(gameName, this);
        myStage.setScene(c.getUserScene());
        myStage.show();
    }

    public void initializeGame(String gameName, List<String> playerNames){
        myStage.setTitle("Game - "+ gameName);
        GameScreen gameScreen = passingController.getGameScreen(gameName, playerNames);
        Scene gameScene = gameScreen.getScene(this);
        myStage.setScene(gameScene);
        myStage.show();
    }

    public void setWinScreen(String gameName, String playerName, int playerScore){
        WinScreen winScreen = new WinScreen(this, gameName, playerName, playerScore, VIEW_WIDTH, VIEW_HEIGHT);
        myStage.setScene(winScreen.getScene());
        myStage.show();
    }



    public double getWidth(){
        return VIEW_WIDTH;
    }

    public double getHeight(){
        return VIEW_HEIGHT;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void setSplash(){
        myStage.setScene(splash);
    }
}
