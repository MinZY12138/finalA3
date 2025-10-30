package game.capabilities;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Fire;

/**
 * <h1>Summon Fire interface</h1>
 * <p>The {@code SummonFire} interface defines a capability for any class that can create a
 * {@link Fire} object onto a specific {@link Location}.</p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-10-05
 */
public interface SummonFire {

    /**
     * Set the ground at the specified location with a fire.
     *
     * @param location the location where the fire will be created
     */
    default void burnLocation(Location location, int duration) {
        location.setGround(new Fire(duration));
    }
}
