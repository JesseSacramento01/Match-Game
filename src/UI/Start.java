package UI;

import Model.GameModel;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;




/**
 * @author Jessé Sacramento
 * @version 10/03/2022
 */

public class Start extends Application {
    static final int WIDTH = 800;
    static final int HEIGHT = 500;

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GameModel model = new GameModel();

        stage = primaryStage;

        settings(stage, model);

        stage.show();

    }

    public void setBackGroundImage( VBox vBox ){
        Image backgroundImg = new Image("File:C:\\Users\\JesseSacramento\\IdeaProjects\\Match-Game\\src\\Resources\\background.png");
        BackgroundImage backgroundImage = new BackgroundImage( backgroundImg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        vBox.setBackground(background);
    }

    public void settings( Stage stage, GameModel model ){

        model.setLevel( model.getLevel() );

        Board board = new Board(model);

        VBox vBox = new VBox();

        HBox hBox = new HBox();
        HBox boardBox = new HBox();

        Label label = new Label();

        label.setText("Level  " + model.getLevel());
        label.setFont(Font.font(24));
        label.setStyle("-fx-text-fill:white;");
        label.setAlignment(Pos.CENTER);

        hBox.getChildren().add(label);

        boardBox.getChildren().add(board.initBoard());

        vBox.getChildren().addAll(hBox, boardBox);
        setBackGroundImage( vBox );
        vBox.setAlignment(Pos.CENTER);

        hBox.setAlignment(Pos.TOP_CENTER);
        boardBox.setAlignment(Pos.BOTTOM_CENTER);



        Scene scene = new Scene(vBox,WIDTH,HEIGHT);

        stage.setScene(scene);

        if (model.getLevel() == 5) {
            newScene();
        }
    }

    public void newScene(){

        VBox vBox = new VBox();

        vBox.getChildren().addAll();
        setBackGroundImage( vBox );
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,WIDTH,HEIGHT);
        stage.setScene(scene);
    }

}
