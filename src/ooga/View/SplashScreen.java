package ooga.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ooga.Main;

import java.util.List;
import java.util.Map;

public class SplashScreen {
    private Paint background = Color.WHITESMOKE;
    private Scene startScene;
    private boolean darkMode = false;
    private String logo = "viewAssets/loading.gif";
    private String style = "-fx-border-color: black;-fx-padding: 2 2 2 2 ";
//    private String logo = "cardDecks/poker/aces.png";
    public static final List<String> GAME_FIELDS = List.of("Solitaire", "Humanity", "TOD", "Memory", "Concentration");


    public Rectangle generateLogo(){
        Rectangle hypno = new Rectangle(200, 120);
        Image hypnoImage = new Image(this.getClass().getClassLoader().getResourceAsStream(logo));
        ImagePattern hypnoImagePattern = new ImagePattern(hypnoImage);
        hypno.setFill(hypnoImagePattern);
        return hypno;
    }

    /***
     * set up the start screen
     * and return it as a scene
     * @param mainView
     */
    public SplashScreen(UserInterface mainView){
        Group startRoot = new Group();
        startScene = new Scene(startRoot, mainView.getWidth(), mainView.getHeight(), background);
        double width = mainView.getWidth();
        double height = mainView.getHeight();
        setupButtons(mainView,startRoot, width/2-70, height/2+100);
        Label Header = new Label("?House of Cards¿");
        Header.setFont(new Font("Garamond", 30));
        Header.setTextFill(Color.DARKCYAN);
        startRoot.getChildren().addAll(Header);
        Header.setLayoutX(width/2-100); Header.setLayoutY(10);
        Rectangle logo = generateLogo();
        logo.setLayoutX(width/2-logo.getWidth()/2); logo.setLayoutY(100);
        startRoot.getChildren().addAll(logo);

        Label rules = new Label("GAME RULES\n" +
                "\t 1. Click on the button to choose the game you want to play. All the games except Memory and Solitaire are multiplayer\n" +
                "\t 2. Press 'E' to load the first pile and 'N' for the next piles in Cards Against Humanity\n" +
                "\t 3. The player changes after every 2 cards in the game Concentration\n" +
                "\t 4. We have implemented Spyder Solitaire so we assume that you are familiar with the rules\n"+
                "\t 5. THE QUESTIONS AND ANSWERS FOR CARDS AGAINST HUMANITY WERE FOUND ONLINE AND NOT MADE BY US!\n"+
                "\t");

        rules.setLayoutX(width/2-350); rules.setLayoutY(220);
        rules.setFont(new Font("Garamond", 15));
        startRoot.getChildren().addAll(rules);
    }

    private void setupButtons(UserInterface mainView, Group sceneGroup, double XPos, double YPos){
        int distanceBetweenButtons=40;
        int initialDistance=0;
        for(String name:GAME_FIELDS){
            ButtonFactory gameButton = new ButtonFactory(name, XPos, YPos+initialDistance );
            gameButton.setOnAction(e->mainView.userScreen(name, darkMode));
            sceneGroup.getChildren().add(gameButton);
            initialDistance+=distanceBetweenButtons;

        }
        ButtonFactory newWorkSpace = new ButtonFactory("New Workspace", (double)1000, 10);
        sceneGroup.getChildren().add(newWorkSpace);
        newWorkSpace.setOnAction(e-> newWindowButton());
        ButtonFactory HighScores = new ButtonFactory("View Highscores", (double) 200, 10);
        sceneGroup.getChildren().add(HighScores);
        HighScores.setOnAction(e-> presentOnScreen(mainView, sceneGroup));

        ButtonFactory darkMode = new ButtonFactory("DarkMode", (double)1000, 40);
        sceneGroup.getChildren().add(darkMode);
        darkMode.setOnAction(e-> darkModeButton());


    }

    private void darkModeButton(){
        if(darkMode){
            startScene.setFill(Color.WHITESMOKE);
            darkMode = false;
        }
        else{
            startScene.setFill(Color.DARKGRAY);
            darkMode = true;
        }

    }
    private void presentOnScreen(UserInterface mainView, Group sceneGroup){
        Map<String, List<String>> highScores=  mainView.getController().getHighScores();
        System.out.println(highScores);
        VboxFactory displayScores = new VboxFactory(0);
        displayScores.setSpacing(20);
        makeHBox(highScores, displayScores);
        displayScores.setLayoutX(30);
        displayScores.setLayoutY(100);
        sceneGroup.getChildren().add(displayScores);
    }

    private void makeHBox(Map<String, List<String>> highScores, VboxFactory displayScores) {
        for (String key: highScores.keySet()){
            List<String> namesAndScores = highScores.get(key);
            HBox displayDetails = new HBox(10);
            Label game = new Label(key);
            Label nameLabel = new Label(namesAndScores.get(0));
            Label scoreLabel = new Label(namesAndScores.get(1));
            displayDetails.setStyle(style);
            displayDetails.getChildren().addAll(game, nameLabel,scoreLabel);
            displayScores.getChildren().add(displayDetails);
          //  String
        }
    }

    private void newWindowButton(){
        Stage newScreen = new Stage();
       UserInterface newSimulation = new UserInterface();
        try {
            newSimulation.start(newScreen);
        } catch (Exception ex) {
//            showMessage(Alert.AlertType.ERROR, ex.getMessage());
        }
    }

    public Scene getStartScene() {
        return startScene;
    }
}

