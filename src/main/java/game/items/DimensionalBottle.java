package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BreakBottleAction;

public class DimensionalBottle extends Item {

    private DimensionalGround dimensionalGround;

    public DimensionalBottle() {
        super(ItemInfo.DIMENSIONAL_BOTTLE.getNAME(),
                ItemInfo.DIMENSIONAL_BOTTLE.getCHAR(),
                ItemInfo.DIMENSIONAL_BOTTLE.isPORTABLE());
    }

    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(new BreakBottleAction(this));
        return actions;
    }

    public void setDimensionalGround(DimensionalGround dimensionalGround) {
        this.dimensionalGround = dimensionalGround;
    }
}
