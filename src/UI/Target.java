package UI;

import Model.Name;
import UI.Position;
import View.View;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */
public class Target extends Button {

    final double WIDTH = 80;
    private Position pos;
    private final ImageView noImage = new ImageView("File:C:\\Users\\JesseSacramento\\IdeaProjects\\Animation\\src\\Resources\\noImage.jpg");
    private final Name name;

    public Target( Position pos, Name name ){
        this.pos = pos;
        this.name = name;
    }

    public void setTargetImage( ImageView imageView ){
        imageView.setFitWidth(WIDTH);
        imageView.setPreserveRatio(true);
        setGraphic(imageView);
    }

    public Position getPos() {
        return pos;
    }

    public Name getName() {
        return name;
    }

    public void cleanImagesTargets(){
        noImage.setFitWidth(WIDTH);
        noImage.setPreserveRatio(true);
        setGraphic(noImage);
    }

}
