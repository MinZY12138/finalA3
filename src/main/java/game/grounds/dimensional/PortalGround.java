package game.grounds.dimensional;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EnterStoreAction;
import game.mysteriostore.DimensionalStoreType;
import game.mysteriostore.MysterioStoreSession;

import java.util.Objects;
/**
 * Base ground that exposes a portal into an active dimensional store.
 */
public class PortalGround extends Ground implements DimensionalSite {

    private final DimensionalStoreType storeType;
    private MysterioStoreSession activeSession;

    public PortalGround(char displayChar, String name, DimensionalStoreType storeType) {
        super(displayChar, name);
        this.storeType = Objects.requireNonNull(storeType);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (activeSession != null && activeSession.isOpen()) {
            actions.add(new EnterStoreAction(activeSession));
        }
        return actions;
    }

    @Override
    public DimensionalStoreType getStoreType() {
        return storeType;
    }

    public void activateStore(MysterioStoreSession session) {
        this.activeSession = Objects.requireNonNull(session);
    }

    public void deactivateStore() {
        this.activeSession = null;
    }
}
