import Model.Name;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */
public class Target extends Button {

    final double WIDTH = 80;
    private Position pos;
    private final ImageView uva = new ImageView("C:\\Users\\JesseSacramento\\IdeaProjects\\Animation\\src\\Resources\\uva.jpg");
    private final ImageView banana = new ImageView("C:\\Users\\JesseSacramento\\IdeaProjects\\Animation\\src\\Resources\\banana.jpg");
    private final ImageView noImage = new ImageView("C:\\Users\\JesseSacramento\\IdeaProjects\\Animation\\src\\Resources\\noImage.jpg");
    private final Name name;

    public Target( Position pos, Name name ){
        this.pos = pos;
        this.name = name;
    }

    public void setUva(){
        uva.setFitWidth(WIDTH);
        uva.setPreserveRatio(true);
        setGraphic(uva);
    }
    public void setBanana(){
        banana.setFitWidth(WIDTH);
        banana.setPreserveRatio(true);
        setGraphic(banana);
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
