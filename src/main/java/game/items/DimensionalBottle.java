package game.items;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.items.ItemInfo;

/**
 * Dimensional Bottle (minimal, TeleportCube-style)
 * Smash on current tile only.
 */
public final class DimensionalBottle extends Item {

    /**
     * Constructor for DimensionalBottle.
     */
    public DimensionalBottle() {
        super(ItemInfo.DIMENSIONAL_BOTTLE.getNAME(),
                ItemInfo.DIMENSIONAL_BOTTLE.getCHAR(),
                ItemInfo.DIMENSIONAL_BOTTLE.isPORTABLE());
    }

    /**
     * Currently no actions are available.
     * You can later add SmashHereAction to make it functional.
     */
    @Override
    public List<Action> allowableActions(Actor owner, Location here, String direction) {
        return List.of(); // No actions yet
    }
}
