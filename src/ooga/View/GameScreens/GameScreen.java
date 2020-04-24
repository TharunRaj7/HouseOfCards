package ooga.View.GameScreens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import ooga.View.ButtonFactory;
import ooga.View.UserInput;
import ooga.View.UserInterface;

import java.util.Objects;

public abstract class GameScreen {
    private ButtonFactory exitButton;
    private ButtonFactory restartButton;
    private double xpos=20;
    private double ypos=600;
    private double endXpos=1030;
    public void setUpButtons(Group gameScene){
        exitButton = new ButtonFactory("Exit", xpos,ypos );
        restartButton = new ButtonFactory("Restart",endXpos,ypos);
        gameScene.getChildren().addAll(exitButton,restartButton);
    }


    public abstract Scene getScene(UserInterface ui);
//    public void userScreen(String gameName){
//        UserInput c= new UserInput(gameName, this);
//        myStage.setScene(c.getUserScene());
//        myStage.show();
//    }

}
