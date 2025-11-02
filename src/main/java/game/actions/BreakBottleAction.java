package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.DimensionalGround;
import game.grounds.RandomLocation;
import game.items.DimensionalBottle;

/**
 * <h1>BreakBottleAction Class</h1>
 * <p>
 * The {@code BreakBottleAction} is an {@link Action} that {@link Actor} can throw
 * {@link DimensionalBottle} on the ground. The ground will turn to
 * {@link DimensionalGround} after throw it.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-11-03
 */
public class BreakBottleAction extends Action {

    /**
     * The dimensional bottle to be thrown.
     */
    private final DimensionalBottle BOTTLE;

    /**
     * The ground that the store's door will appear.
     */
    private final DimensionalGround DIMENSIONAL_GROUND;

    /**
     * The distance of throwing the dimensional bottle.
     */
    private static final int THROW_DISTANCE = 1;

    /**
     * Constructor of the BreakBottleAction class.
     *
     * @param bottle         the dimensional bottle to be thrown
     * @param targetLocation the ground that the store's door will appear
     */
    public BreakBottleAction(DimensionalBottle bottle, DimensionalGround targetLocation) {
        this.BOTTLE = bottle;
        this.DIMENSIONAL_GROUND = targetLocation;
    }

    /**
     * This method will handle the logic of throwing dimensional bottle.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string message represents actors throw the dimensional bottle
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = RandomLocation.randomChooseSurrounding(map.locationOf(actor),
                THROW_DISTANCE);

        location.setGround(DIMENSIONAL_GROUND);

        actor.removeItemFromInventory(BOTTLE);

        DIMENSIONAL_GROUND.setSourceLocation(location);
        return actor + " shattered a Dimensional Bottle";
    }

    /**
     * Description of this action that shows on the menu.
     *
     * @param actor The actor performing the action.
     * @return a short description shows on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " throws a Dimensional Bottle";
    }
}
