package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Class represent TeleportAction</h1>
 *
 * <p>
 *     Represent an action to telepot. It allowed {@link Actor}
 *     to teleport to a certain destination {@link Location}.
 * </p>
 *
 * Extends {@link Action}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class TeleportAction extends Action
{
    /**
     * The object to use to teleport.
     */
    private final Teleportable OBJECT;

    /**
     * The actor who may perform teleporting.
     */
    private final Actor ACTOR;

    /**
     * The destination to teleport to.
     */
    private final Location DESTINATION;

    /**
     * Constructor for TeleportAction
     * @param object The object to use to teleport.
     * @param actor The actor who may perform teleporting.
     * @param destination The destination to teleport to.
     */
    public TeleportAction(Teleportable object, Actor actor, Location destination)
    {
        this.OBJECT = object;
        this.ACTOR = actor;
        this.DESTINATION = destination;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        map.moveActor(this.ACTOR, this.DESTINATION);
        return OBJECT.teleportTo(this.ACTOR, this.DESTINATION);
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return this.OBJECT.getMenuDescription(actor, this.DESTINATION);
    }
}
