package game.mysteriostore;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.LeaveMysterioStoreAction;
import game.grounds.GroundInfo;

/**
 * Portal ground placed inside the store that lets visitors return to the original map.
 */
public final class StoreExitGround extends Ground {

    private MysterioStoreSession session;

    public StoreExitGround() {
        super(GroundInfo.TELEPORTATION_CIRCLE.getDISPLAY_CHAR(), GroundInfo.TELEPORTATION_CIRCLE.getNAME());
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (session != null && session.isOpen() && location.containsAnActor() && location.getActor() == actor) {
            actions.add(new LeaveMysterioStoreAction(session));
        }
        return actions;
    }

    void bind(MysterioStoreSession session) {
        this.session = session;
    }

    void unbind() {
        this.session = null;
    }
}
