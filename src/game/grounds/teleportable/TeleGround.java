package game.grounds.teleportable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actions.Teleportable;

import java.util.List;

/**
 * <h1>Abstract class represent TeleGround</h1>
 *
 * <p>
 *     Representing any ground type that can use to teleport.
 * </p>
 *
 * Extends {@link Ground}
 * Implements {@link Teleportable}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public abstract class TeleGround extends Ground implements Teleportable
{
    /**
     * A list of {@link Location} indicate this can teleport to where.
     */
    private final List<Location> DESTINATION;

    /**
     * Constructor for TeleGround
     * @param destination A list of {@link Location} indicate this can teleport to where.
     * @param displayCharacter character to display for this type of terrain
     * @param name name of this terrain.
     */
    public TeleGround (List<Location> destination, char displayCharacter, String name)
    {
        super(displayCharacter, name);
        this.DESTINATION = destination;
    }

    /**
     * Use to burn its surrounding (use by method
     * {@link Teleportable#teleportTo(Actor actor, Location destination)})
     * @param destination the place to burn its surrounding
     */
    protected abstract void burnSurrounding (Location destination);

    /**
     * Get all the destination that this can teleport to.
     * @return A list of {@link Location}
     */
    protected List<Location> getDestination()
    {
        return this.DESTINATION;
    }

    /**
     * Get all the possible TeleportAction for all its {@code DESTINATION}.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return all the possible {@link TeleportAction} for all its {@code DESTINATION}
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location,
                                       String direction)
    {
        ActionList actionList = super.allowableActions(actor, location, direction);

        //Loop through all its destination
        for (Location destination : this.getDestination())
        {
            actionList.add(new TeleportAction(this, actor, destination));
        }
        return actionList;
    }

    /**
     * Describe what action will be performed if this Action is chosen in the
     * menu.
     * @param actor the actor who interact with this object.
     * @param location the location to teleport to (may not be use for all
     *                 teleportable type).
     * @return the action description to be displayed on the menu
     */
    @Override
    public String getMenuDescription(Actor actor, Location location)
    {
        return actor + " will teleport to " + location + ".";
    }
}
