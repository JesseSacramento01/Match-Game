package UI;

import Model.GameModel;
import Model.Name;
import View.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


import java.util.*;

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


    GameModel model;

    public Board(GameModel model) {
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
                model.disappear(target);
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
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            names.add(UVA);
            names.add(BANANA);
        }

        Collections.shuffle(names);

        return names;
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

            model.setTargetImage(target); // set the image to appear in the board

            model.checkMatchImage(); // check if the images match

            model.setTargetList(target); // setUp of the target List

            model.checkGame(); // check if the player made the right match in all images

        }
    }

    /**
     * Method that verifies if both recent target images are equals
     * this.count % 2 == 0 because of the match images
     * the condition !equals will make sure that the objects are not the same but both have the same image
     * in the switch there's just the second target because the first will be already selected
     */


    public void setImages() {
        switch (model.getFirst().getName()) {
            case UVA -> {
                model.getSecond().setUva();
                model.getFirst().setDisable(true);
                model.getSecond().setDisable(true);
            }
            case BANANA -> {
                model.getSecond().setBanana();
                model.getFirst().setDisable(true);
                model.getSecond().setDisable(true);
            }
        }
    }



}
