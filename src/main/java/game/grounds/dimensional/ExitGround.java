package game.grounds.dimensional;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.LeaveStoreAction;
import game.grounds.GroundInfo;
import game.mysteriostore.MysterioStoreSession;

import java.util.Objects;

/**
 * Ground placed at the entrance of a dimensional store, allowing visitors to return to their original map.
 */
public final class ExitGround extends Ground {

    private MysterioStoreSession activeSession;


    public ExitGround() {
        super(GroundInfo.EXIT.getDISPLAY_CHAR(), GroundInfo.EXIT.getNAME());
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (activeSession != null && activeSession.isOpen()) {
            actions.add(new LeaveStoreAction(activeSession));
        }
        return actions;
    }

    public void setActiveSession(MysterioStoreSession session) {
        this.activeSession = Objects.requireNonNull(session);
    }

    public void clearActiveSession(MysterioStoreSession session) {
        if (activeSession == session) {
            activeSession = null;
        }
    }
}