package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.RandomLocation;

import java.util.Random;

/**
 * <h1>Class represent TeleportAction</h1>
 *
 * <p>
 * Represent an action to telepot. It allowed {@link Actor}
 * to teleport to a certain destination {@link Location}.
 * </p>
 * <p>
 * Extends {@link Action}
 *
 * @author Shee Seng Cheng
 * @version 1.1
 * <p>
 * Modified by: Tay Chee Hsian
 */
public class TeleportAction extends Action {

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
     * Indicate this execution with the current teleportable
     * object successful rate.
     */
    private final double SUCCESSFUL_RATE;

    /**
     * Use to get a random number between 0-99 to compare with the
     * successful rate.
     */
    private final static Random RANDOM = new Random();

    /**
     * Constructor for TeleportAction
     *
     * @param object         The object to use to teleport.
     * @param actor          The actor who may perform teleporting.
     * @param destination    The destination to teleport to.
     * @param successfulRate The possibility of this action being
     *                       successfully executed
     */
    public TeleportAction(Teleportable object, Actor actor,
                          Location destination, double successfulRate) {
        this.OBJECT = object;
        this.ACTOR = actor;
        this.DESTINATION = destination;
        this.SUCCESSFUL_RATE = successfulRate;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened
     * (the result of the action being performed)
     * that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Default message and destination
        String defaultMessage = actor + " has teleport to a random " +
                "place due to malfunction of " + this.OBJECT.getSimpleName();
        Location chosenDestination = this.DESTINATION;

        //If this execution success modify the default message
        int MAXIMUM_BOUND = 100;
        if ((RANDOM.nextInt(MAXIMUM_BOUND) <= this.SUCCESSFUL_RATE)) {
            defaultMessage = OBJECT.teleportTo(this.ACTOR, this.DESTINATION);
        } else {
            //Get a random position within this current map
            chosenDestination = RandomLocation.randomChooseLocation(map);
        }

        //Teleport the actor and return the message after execution.
        map.moveActor(this.ACTOR, chosenDestination);
        return defaultMessage;
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + this.DESTINATION + " using " +
                this.OBJECT.getSimpleName();
    }
}
