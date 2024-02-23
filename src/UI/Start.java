package UI;

import Model.GameModel;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.lang.System.exit;

/**
 * @author Jessé Sacramento
 * @version 10/03/2022
 */

public class Start extends Application {
    static final int WIDTH = 800;
    static final int HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button startButton = new Button("Start Game");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");

        exitButton.setOnAction((actionEvent) -> exit(0));

        GameModel model = new GameModel();
        Board board = new Board(model);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(board.initBoard(), startButton,optionsButton,exitButton);
        setBackGroundImage( vBox );
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,WIDTH,HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void setBackGroundImage( VBox vBox ){
        Image backgroundImg = new Image("File:C:\\Users\\JesseSacramento\\IdeaProjects\\Match-Game\\src\\Resources\\background.png");
        BackgroundImage backgroundImage = new BackgroundImage( backgroundImg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        vBox.setBackground(background);
    }



}
