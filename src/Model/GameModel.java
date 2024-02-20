package Model;


import UI.Target;
import View.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Jessé Sacramento
 * @version 30/01/2024
 */


public class GameModel {
    private View view;
    private int count = 0;
    private Target first;
    private Target second;

    Set<Target> targets = new HashSet<>();

    final static int BOARD_SIZE = 4;


    public void setView(View view) {
        this.view = view;
    }
    public void setTargetImage(Target target) {
        count += 1;

        if (count % 2 == 0) {
            setFirst(target);
        } else {
            setSecond(target);
        }

        switch (target.getName()) {
            case UVA -> target.setUva();
            case BANANA -> target.setBanana();
        }
    }


    public void checkMatchImage() {
        if (first != null && second != null)
            if (this.count % 2 == 0 && this.count != 0) {
                if (this.first.getName() == this.second.getName() && !first.equals(second)) {
                    view.setImages();
                }
                else {
                    view.disappearOnWrongTarget(getFirst());
                    view.disappearOnWrongTarget(getSecond());
                }
            }
    }

    public Target getFirst() {
        return first;
    }

    public Target getSecond() {
        return second;
    }

    public void setFirst(Target first) {
        this.first = first;
    }

    public void setSecond(Target second) {
        this.second = second;
    }

    /**
     * @param target param to add to the List, and do the necessary setup
     */
    public void setTargetList(Target target) {
        targets.add(target);
    }

    /**
     * gameWon Method will check if all buttons are disabled
     * that means that all the images were selected correctly
     * if the counter of the disabled images is equal to the size of targets list
     * it will return true otherwise false
     *
     * @param targets the buttons to be clicked
     * @return returns the boolean value counter == targets.size()
     */
    public static boolean gameWon(Set<Target> targets) {
        int counter = 0;

        for (Target target : targets) {
            if (target.isDisabled()) {
                counter += 1;
            }
        }

        return counter == targets.size();

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
     * @param target is the button that will be clicked to find the right image
     */
    public void disappear( Target target ) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> target.cleanImagesTargets()));
        timeline.play();
    }
}
