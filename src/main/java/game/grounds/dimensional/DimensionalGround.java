package game.grounds.dimensional;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.EnterMysterioStoreAction;
import game.grounds.GroundInfo;
import game.mysteriostore.MysterioStoreDirectory;
import game.mysteriostore.MysterioStoreSession;

import java.util.Objects;

/**
 * Ground transformed by a shattered dimensional bottle.
 */
public class DimensionalGround extends Ground implements DimensionalSite {

    private static final int STORE_SPAWN_DELAY = 3;
    private static final int STORE_ACTIVE_DURATION = 3;

    private final Ground consumedGround;
    private final DimensionalStoreType storeType;

    private int ticks;
    private boolean storeManifested;
    private MysterioStoreSession activeSession;

    public DimensionalGround(Ground consumedGround, DimensionalStoreType storeType) {
        super(GroundInfo.DIMENSIONAL_GROUND.getDISPLAY_CHAR(), GroundInfo.DIMENSIONAL_GROUND.getNAME());
        this.consumedGround = Objects.requireNonNull(consumedGround);
        this.storeType = Objects.requireNonNull(storeType);
    }

    @Override
    public void tick(Location location) {
        ticks++;

        if (!storeManifested) {
            if (ticks >= STORE_SPAWN_DELAY) {
                storeManifested = true;
                activeSession = MysterioStoreDirectory.open(storeType, location);
            }
        } else if (ticks >= STORE_SPAWN_DELAY + STORE_ACTIVE_DURATION) {
            if (activeSession != null) {
                activeSession.forceClose();
                activeSession = null;
            }
            location.setGround(consumedGround);
        }
    }

    @Override
    public DimensionalStoreType getStoreType() {
        return storeType;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        if (storeManifested && activeSession != null && activeSession.isOpen() && location.containsAnActor()) {
            Actor occupant = location.getActor();
            if (occupant == actor) {
                actions.add(new EnterMysterioStoreAction(activeSession));
            }
        }

        return actions;
    }

    public Ground getConsumedGround() {
        return consumedGround;
    }

    public int getTicksElapsed() {
        return ticks;
    }

    public boolean isStoreManifested() {
        return storeManifested;
    }

    public int getStoreSpawnDelay() {
        return STORE_SPAWN_DELAY;
    }

    public int getStoreActiveDuration() {
        return STORE_ACTIVE_DURATION;
    }

    public MysterioStoreSession getActiveSession() {
        return activeSession;
    }
}