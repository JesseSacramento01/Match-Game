package Model;


import UI.Start;
import UI.Target;
import View.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    private int boardSizeRow = 2;
    private int boardSizeCol = 2;

    private Map<Name, String> dirNameMap = new HashMap<>();

    private boolean won = false;
    private static int level = 1;


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

    /**
     * When the first target is clicked and the second is not a match both images will
     * disappear
     */
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


    /**
     * gameWon Method will check if all buttons are disabled
     * that means that all the images were selected correctly
     * if the counter of the disabled images is equal to the size of targets list
     * it will return true otherwise false
     *
     * @param targets the buttons to be clicked
     * @return returns the boolean value counter == targets.size()
     */
    public boolean gameWon(Set<Target> targets){

        for (Target target : targets) {
            if( !target.isDisable() )
                return false;
        }

        return true;

    }

    /**
     * Will check if all the images are selected using the result of the gameWon
     */
    public void checkGame() {
        if (targets.size() == boardSizeRow * boardSizeCol) {

            if (gameWon(targets)) {
                setWon(true);
                System.out.println("You Won!");
                nextLevel(); // to increase the variable level
                setLevel(level);
                Start start = new Start();
                start.start( Start.stage );
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

    /**
     * This method will get the directories stored in a xml file
     * then a map with the Name of the Target which is the image tha appears when the
     * button is clicked and the directories map<Name, String> it's necessary to iterate
     * to get the names that match with the file name of the image
     * e.g., cube.jpg the name in the class Name is CUBE it's necessary also to use
     * lowercase() function
     *
     */
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



    public static void main(String[] args) {


    }

    /**
     * @return return a list of names that identify which images are related with the target
     */
    public List<Name> namesOfTargets() {

        List<Name> targetNames = new ArrayList<>();
        List<Name> names = Name.targetList();

         // to get random images in the process of get the images

        Collections.shuffle(names);

        targetNames.addAll(names.subList(0,(boardSizeRow * boardSizeCol) / 2));
        targetNames.addAll(names.subList(0, (boardSizeRow * boardSizeCol) / 2));

        Collections.shuffle(targetNames);

        System.out.println(targetNames);


        return targetNames;
    }

    /**
     * This method will set The board size for the next level
     * @param level variable that stores the level number
     */
    public void setLevel( int level ){
        switch (level){
            case 1:
                setBoardSizeRow(1);
                setBoardSizeCol(2);
                break;
            case 2:
                setBoardSizeRow(2);
                setBoardSizeCol(2);
                break;
            case 3:
                setBoardSizeRow(2);
                setBoardSizeCol(3);
                break;
            case 4:
                setBoardSizeRow(4);
                setBoardSizeCol(3);
                break;
            case 5:



            default:
                break;
        }
    }

    /**
     * this method will increase the number referenced by the level variable
     */
    public void nextLevel(){
        if (isWon()){
            // to go to the next level using the variable and inserting in
            //the method setLevel
            level++;
        }
    }


    public int getBoardSizeRow() {
        return boardSizeRow;
    }

    public int getBoardSizeCol() {
        return boardSizeCol;
    }

    public Map<Name, String> getDirNameMap() {
        return dirNameMap;
    }

    public Target getFirst() {
        return first;
    }

    public Target getSecond() {
        return second;
    }

    public boolean isWon() {
        return won;
    }

    public int getLevel() {
        return level;
    }

    public void setDirNameMap(Map<Name, String> dirInfo) {
        this.dirNameMap = dirInfo;
    }


    public void setBoardSizeRow(int boardSizeRow) {
        this.boardSizeRow = boardSizeRow;
    }

    public void setBoardSizeCol(int boardSizeCol) {
        this.boardSizeCol = boardSizeCol;
    }

    public void setImageView(Name name, Target target) {

        String directory = getDirNameMap().get(name);
        target.setTargetImage(directory);
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

    public void setWon(boolean won) {
        this.won = won;
    }
}
