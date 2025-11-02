package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BreakBottleAction;

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
     * @param location the location of the ground on which the item lies
     * @return a break bottle action
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(new BreakBottleAction(this));
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
