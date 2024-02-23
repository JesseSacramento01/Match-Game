package Model;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 26/01/2024
 */
public enum Name {
     UVA,BANANA,CANDLE,RUBIK_CUBE,SHOE,VOLCANO;

    public static List<Name> targetList(){
        return Arrays.asList(UVA,BANANA,CANDLE,RUBIK_CUBE,SHOE,VOLCANO);
    }
}
