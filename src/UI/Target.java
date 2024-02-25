package UI;

import Model.Name;
import javafx.scene.control.Button;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */
public class Target extends Button {

    private final Name name;

    public Target( Name name ){
        this.name = name;
    }

    public void setTargetImage( String directory ){
        setStyle("-fx-background-image: url('" + directory +"');" +
                "-fx-background-size: cover;" +
                "-fx-border-color: #605060;" +
                "-fx-border-width: 1px;");

    }

    public Name getName() {
        return name;
    }

    public void cleanImagesTargets(){
        setStyle("-fx-background-image: url('" + "Resources/gray_background.jpg" +"');" +
                "-fx-background-size: cover;" +
                "-fx-border-color: #605060;" +
                "-fx-border-width: 1px;");
    }

}
