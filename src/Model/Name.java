package Model;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */
public enum Name {
     CANDLE,RUBIK_CUBE,SHOE,VOLCANO,PIECE_OF_ORANGE,ORANGE,HAMBURGER,SMURF,ROAD,SMURF_EATING,
     SHOES,CUBE;

    public static List<Name> targetList(){
        return Arrays.asList(
                CANDLE,RUBIK_CUBE,SHOE,VOLCANO,
                PIECE_OF_ORANGE, ORANGE, HAMBURGER,
                SMURF, ROAD, SMURF_EATING, SHOES, CUBE);
    }
}
