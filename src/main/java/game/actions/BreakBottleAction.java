package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.DimensionalBottle;

import java.util.Objects;

/**
 * <h1>Shatter Dimensional Bottle Action</h1>
 *
 * <p>
 * Breaks a {@link DimensionalBottle} at a chosen location, consuming the item and transforming
 * the ground beneath into dimensional terrain.
 * </p>
 */
public class BreakBottleAction extends Action {

    private final DimensionalBottle bottle;
    private final Location target;

    /**
     * Constructs the action targeting the provided location.
     *
     * @param bottle the dimensional bottle to shatter
     * @param target the location where the bottle will break
     */
    public BreakBottleAction(DimensionalBottle bottle, Location target) {
        this.bottle = Objects.requireNonNull(bottle);
        this.target = Objects.requireNonNull(target);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        if (actor.getItemInventory().contains(bottle)) {
            actor.removeItemFromInventory(bottle);
        } else if (actorLocation.getItems().contains(bottle)) {
            actorLocation.removeItem(bottle);
        }
        return bottle.shatterAt(actor, target);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " shatters " + bottle + " at " + target;
    }
}