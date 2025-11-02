package game.grounds.dimensional;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundInfo;
import game.mysteriostore.DimensionalStoreType;

import java.util.Objects;

/**
 * Ground transformed by a shattered dimensional bottle.
 */
public class DimensionalGround extends Ground implements DimensionalSite {

    private static final int STORE_SPAWN_DELAY = 3;
    private static final int STORE_ACTIVE_DURATION = 3;

    private final Ground consumedGround;
    private final DimensionalStoreType storeType;
    private final StoreLifecycle lifecycle;

    private int ticks;
    private boolean storeManifested;

    public DimensionalGround(Ground consumedGround, DimensionalStoreType storeType, StoreLifecycle lifecycle) {
        super(GroundInfo.DIMENSIONAL_GROUND.getDISPLAY_CHAR(), GroundInfo.DIMENSIONAL_GROUND.getNAME());
        this.consumedGround = Objects.requireNonNull(consumedGround);
        this.storeType = Objects.requireNonNull(storeType);
        this.lifecycle = Objects.requireNonNull(lifecycle);
    }

    @Override
    public void tick(Location location) {
        ticks++;

        if (!storeManifested) {
            if (ticks >= STORE_SPAWN_DELAY) {
                storeManifested = true;
                lifecycle.onOpen(this, location);
            }
        } else if (ticks >= STORE_SPAWN_DELAY + STORE_ACTIVE_DURATION) {
            lifecycle.onClose(this, location);
            location.setGround(consumedGround);
        }
    }

    @Override
    public DimensionalStoreType getStoreType() {
        return storeType;
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
}