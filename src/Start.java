import Model.GameModel;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
        Image backgroundImg = new Image("C:\\Users\\JesseSacramento\\IdeaProjects\\Animation\\src\\Resources\\background.png");
        BackgroundImage backgroundImage = new BackgroundImage( backgroundImg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);

        GameModel model = new GameModel();

        Board board = new Board(model);

        VBox vBox = new VBox();
        vBox.getChildren().add(board.initBoard());
        vBox.setBackground(background);

        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,WIDTH,HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();



    }



}
