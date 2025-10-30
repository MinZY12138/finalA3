package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Interface Teleportable</h1>
 *
 * <p>
 *     Represent a teleportable object in the system.
 *     Classes that implement this will need to implement
 *     behaviour that how they react when an actor use
 *     or enter them by method teleportTo.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 2.0
 */
public interface Teleportable
{
    /**
     * Define behaviour for teleport to a certain place.
     * @param actor the actor who interact with this teleportable object.
     * @param destination the location teleport to.
     */
    String teleportTo(Actor actor, Location destination);

    /**
     * Get a simple representation name of the current object.
     * @return {@code String} name representation of this object.
     */
    String getSimpleName();
}
