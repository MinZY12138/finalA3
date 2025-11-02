package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BreakBottleAction;
import game.grounds.DimensionalGround;

/**
 * <h1>DimensionalBottle Class</h1>
 * <p>
 * The {@code DimensionalBottle} is an {@link Item} that actors can throw it on the ground.
 * The ground will turn to the {@link DimensionalGround} and then produce a door leading to
 * a mystery store after three turns.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-11-03
 */
public class DimensionalBottle extends Item {

    /**
     * The ground that the store's door will appear.
     */
    private DimensionalGround dimensionalGround;

    /**
     * Constructor of the DimensionalGround.
     */
    public DimensionalBottle() {
        super(ItemInfo.DIMENSIONAL_BOTTLE.getNAME(),
                ItemInfo.DIMENSIONAL_BOTTLE.getCHAR(),
                ItemInfo.DIMENSIONAL_BOTTLE.isPORTABLE());
    }
    /**
     * This method allows actor can throw the dimensional bottle on the ground.
     *
     * @param owner the actor that owns the item
     * @param map   the map where the actor is performing the action on
     * @return a break bottle action
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map)
    {
        ActionList actions = super.allowableActions(map.locationOf(owner));
        actions.add(new BreakBottleAction(this, dimensionalGround));
        return actions;
    }

    /**
     * Set up the dimensional ground.
     *
     * @param dimensionalGround the ground that will appear store's door
     */
    public void setDimensionalGround(DimensionalGround dimensionalGround) {
        this.dimensionalGround = dimensionalGround;
    }
}
