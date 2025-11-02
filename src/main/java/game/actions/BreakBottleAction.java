package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.RandomLocation;
import game.items.DimensionalBottle;

public class BreakBottleAction extends Action {

    private final DimensionalBottle BOTTLE;

    private final DimensionalGround TARGET_LOCATION;

    private static final int THROW_DISTANCE = 1;

    public BreakBottleAction(DimensionalBottle bottle, DimensionalGround targetLocation) {
        this.BOTTLE = bottle;
        this.TARGET_LOCATION = targetLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = RandomLocation.randomChooseSurrounding(map.locationOf(actor),
                THROW_DISTANCE);
        location.removeItem(BOTTLE);
        location.setGround(TARGET_LOCATION);
        return actor + " shattered a Dimensional Bottle";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " throws a Dimensional Bottle";
    }
}
