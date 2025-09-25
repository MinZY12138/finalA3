package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;

import java.util.Random;


public class RandomLocation {

    private static final Random RAND = new Random();

    public static Location randomChooseLocation(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        int randomX = RAND.nextInt(x.min(), x.max() + 1);
        int randomY = RAND.nextInt(y.min(),y.max() + 1);
        return map.at(randomX, randomY);
    }
}
