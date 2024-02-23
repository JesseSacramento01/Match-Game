package View;

import UI.Board;
import UI.Target;

/**
 * @author Jessé Sacramento
 * @version 30/01/2024
 */
public interface View {
    void disappearOnWrongTarget(Target target);
    void setImages();
    Board initBoard();
}
