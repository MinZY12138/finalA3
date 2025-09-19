package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actions.Teleportable;

import java.util.Collections;
import java.util.List;

/**
 * <h1>Class represent TeleportCube</h1>
 *
 * <p>
 *     Represent a teleport cube in the system.
 *     It can carried by {@link Actor} allowing them
 *     to use it teleport to a certain places with a
 *     50% changes of malfunction.
 * </p>
 *
 * Extends {@link Item}
 * Implements {@link Teleportable}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class TeleportCube extends Item implements Teleportable
{
    private final List<Location> DESTINATION;

    /***
     * Constructor for TeleportCube.
     */
    public TeleportCube(List<Location> destination)
    {
        super("Teleport Cube", '□', true);
        this.DESTINATION = destination;
    }

    public List<Location> getDestination()
    {
        return Collections.unmodifiableList(this.DESTINATION);
    }
    /**
     * Define behaviour for teleport to a certain place.
     *
     * @param actor       the actor who interact with this teleportable object.
     * @param destination the location teleport to.
     */
    @Override
    public String teleportTo(Actor actor, Location destination)
    {
        return actor + " has teleport to " + destination;
    }

    /**
     * Get a simple name represent this object.
     * @return {@code String} Name of this object
     */
    @Override
    public String getSimpleName()
    {
        return this.toString();
    }


    /**
     * List of allowable actions that the item can perform to its owner
     * or to the current map while being carried by an actor
     * Example #1: a healing item can have a special skill that can increase the
     * current actor's hitpoints.
     *
     * @param owner the actor that owns the item
     * @param map   the map where the actor is performing the action on
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map)
    {
        ActionList actionList = super.allowableActions(owner, map);

        //Loop through all its destination
        for (Location destination : this.getDestination())
        {
            //Check if the destination doesn't have actor and can enter by owner.
            if ((! destination.containsAnActor()) && destination.canActorEnter(owner))
            {
                actionList.add(new TeleportAction(this, owner,
                        destination, 50));
            }
        }
        return actionList;
    }
}
