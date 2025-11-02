package game.mysteriostore;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EnterStoreAction;
import game.grounds.GroundInfo;
import game.grounds.dimensional.DimensionalStoreType;

public class PortalGround extends Ground {
    private final DimensionalStoreType type;

    public PortalGround(DimensionalStoreType type) {

        super(GroundInfo.PORTAL.getDISPLAY_CHAR(), GroundInfo.PORTAL.getNAME());
        this.type = type;
    }

    @Override
    public boolean canActorEnter(Actor actor) { return true; }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return java.util.List.of(new EnterStoreAction(type, location));
    }
}
