import Model.GameModel;
import Model.Name;
import Model.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Model.Name.BANANA;
import static Model.Name.UVA;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */

public class Board extends GridPane implements View {

    final static int TARGET_WIDTH = 10;
    final static int TARGET_HEIGHT = 10;
    final static int BOARD_SIZE = 4;
    int count = 0;
    Target first;
    Target second;
    List<Target> targets = new ArrayList<>();
    GameModel model;

    public Board(GameModel model){
        this.model = model;
        model.setView(this);
    }

    // to get the layoutX using the size of the pane calculating how much space is free in order to start in the right place
    // divided by two to put the target at the center of the pane

    /**
     * @return return a board created with a certain size
     */
    public Board initBoard() {
        List<Name> names = namesOfTarget();
        int times = 0;
        Board board = new Board(model);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                Target target = new Target(new Position(i, j), names.get(times));

                buttonSettings(target); // the method that change the setting of the button
                board.add(target, j, i);
                disappear(target);
                times += 1;

                ButtonHandler handler = new ButtonHandler();

                target.setOnAction(handler);


            }
        }

        boardSetting(board);
        return board;
    }

    public void buttonSettings(Target target) {
        target.setPrefHeight(TARGET_HEIGHT);
        target.setPrefWidth(TARGET_WIDTH);
        target.setStyle("-fx-background-color: #FFFFF8; -fx-text-fill: black; -fx-border-color: #000000;" +
                " -fx-border-width: 1px;");


        if (target.getName().equals(UVA)) {
            target.setUva();
        } else {
            target.setBanana();
        }

    }

    /**
     * @param board the board is an object extending to grid pane
     */
    public void boardSetting(Board board) {
        board.setAlignment(Pos.CENTER);
    }

    /**
     * @return return a list of names that identify which images are related with the target
     */
    public List<Name> namesOfTarget() {
        List<Name> names = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE ; i++) {
            names.add(UVA);
            names.add(BANANA);
        }

        Collections.shuffle(names);

        return names;
    }

    /**
     * @param target is the button that will be clicked to find the right image
     */
    public void disappear(Target target) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> target.cleanImagesTargets()));
        timeline.play();
    }

    /**
     * @param target is the button that will be clicked to find the right image
     */
    public void disappearOnWrongTarget(Target target) {
        switch (target.getName()) {
            case UVA -> target.setUva();
            case BANANA -> target.setBanana();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), actionEvent -> target.cleanImagesTargets()));
        timeline.play();
    }

    /**
     * Class that has the method handle to handle the actions done on the button
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Target target = (Target) actionEvent.getSource();

            generateTargetImage(target); // set the image to appear in the board

            System.out.println(target.getName());

            matchImage(); // check if the images match

            setTargetList(target); // setUp of the target List

            checkGame(); // check if the player made the right match in all images

        }
    }

    /**
     * Method that verifies if both recent target images are equals
     * this.count % 2 == 0 because of the match images
     * the condition !equals will make sure that the objects are not the same but both have the same image
     * in the switch there's just the second target because the first will be already selected
     */
    public void matchImage() {
        if (this.count % 2 == 0 && this.count != 0) {
            if (this.first.getName() == this.second.getName() && !first.equals(second)) {
                switch (first.getName()) {
                    case UVA -> {
                        second.setUva();
                        first.setDisable(true);
                        second.setDisable(true);
                    }
                    case BANANA -> {
                        second.setBanana();
                        first.setDisable(true);
                        second.setDisable(true);
                    }
                }
            } else {
                disappearOnWrongTarget(first);
                disappearOnWrongTarget(second);
            }
        }
    }

    public static boolean gameWon(List<Target> targets) {
        int counter = 0;

        for (Target target : targets) {
            if (target.isDisabled()) {
                counter += 1;
            }
        }

        return counter == targets.size();
    }

    public boolean noEqualTarget(Target target, List<Target> targets) {
        boolean equal = false;
        if (!targets.isEmpty()) {
            for (Target value : targets) {
                if (!target.equals(value)) {
                    equal = true;
                } else {
                    equal = false;
                    break;
                }
            }
            //if ( tmp != target){ // add just in case that the is not the same target clicked two times or more
            //targets.add(target)
        }
        return equal;
    }

    /**
     * Will check if all the images are selected using the result of the gameWon
     */
    public void checkGame() {
        if (targets.size() == BOARD_SIZE * BOARD_SIZE) {

            if (gameWon(targets)) {
                System.out.println("You Won!");
            } else {
                System.out.println("You Can do it!");
            }
        }
    }

    /**
     * @param target param to add to the List, and do the necessary setup
     */
    public void setTargetList(Target target) {
        boolean noEqualTarget;

        if (targets.isEmpty()) {
            targets.add(target);
        }

        noEqualTarget = noEqualTarget(target, targets);

        //make sure that the target pressed more than 2 times is not being inserted again in the list
        if (noEqualTarget) {
            targets.add(target);
        }
    }

    public void generateTargetImage(Target target) {
        count += 1;

        if (count % 2 == 0) {
            first = target;
        } else {
            second = target;
        }

        switch (target.getName()) {
            case UVA -> target.setUva();
            case BANANA -> target.setBanana();
        }
    }

}
