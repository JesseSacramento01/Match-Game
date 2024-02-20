package UI;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */
public class Position {

    private int row;
    private int col;

    public Position( int row, int col ){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
