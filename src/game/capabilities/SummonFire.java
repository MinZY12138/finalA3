package game.capabilities;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Fire;

public interface SummonFire
{
    default void burnLocation(Location location) {
        location.setGround(new Fire());
    }
}
