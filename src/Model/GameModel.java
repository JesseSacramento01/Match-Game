package Model;


import UI.Target;
import View.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;



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

    final static int BOARD_SIZE = 2;

    private Map<Name, String> dirNameMap = new HashMap<>();


    public GameModel() {
        createMapDirectory();
    }


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

        setImageView(target.getName(), target);
    }


    public void checkMatchImage() {
        if (first != null && second != null)
            if (this.count % 2 == 0 && this.count != 0) {
                if (this.first.getName() == this.second.getName() && !first.equals(second)) {
                    view.setImages();
                } else {
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
    public void disappear(Target target) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> target.cleanImagesTargets()));
        timeline.play();
    }

    public void createMapDirectory() {
        List<Name> names = Name.targetList();

        XmlHandler xmlHandler = new XmlHandler();
        List<String> directories = xmlHandler.xmlParser();

        Map<Name, String> dirMap = new HashMap<>();

        String lowercaseName;
        String[] dir;

        for (Name name : names) {
            for (int j = 0; j < names.size(); j++) {
                lowercaseName = name.toString().toLowerCase() + ".jpg";

                // get the last part of the directory which is the filename
                dir = directories.get(j).split("/");


                if (dir[2].equals(lowercaseName)) {

                    dirMap.put(name, directories.get(j));
                }
            }
        }
        setDirNameMap(dirMap);
    }


    public void setImageView(Name name, Target target) {

        String directory = getDirNameMap().get(name);

        ImageView imageView = new ImageView(directory);

        target.setTargetImage(imageView);
    }

    public static void main(String[] args) {


    }

    public void setDirNameMap(Map<Name, String> dirInfo) {
        this.dirNameMap = dirInfo;
    }

    public Map<Name, String> getDirNameMap() {
        return dirNameMap;
    }


    /**
     * @return return a list of names that identify which images are related with the target
     */
    public List<Name> namesOfTargets() {

        List<Name> targetNames = new ArrayList<>();
        List<Name> names = Name.targetList();

        Collections.shuffle(names);

        targetNames.addAll(names.subList(0,BOARD_SIZE));
        targetNames.addAll(names.subList(0, BOARD_SIZE));

        return targetNames;
    }

}
