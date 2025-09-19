package game;

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
 * @version 1.0
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
     * Describe what action will be performed if this Action is chosen in the
     * menu.
     * @param actor the actor who interact with this object.
     * @param location the location to teleport to (may not be use for all
     *                 teleportable type).
     * @return the action description to be displayed on the menu
     */
    String getMenuDescription(Actor actor, Location location);
}
