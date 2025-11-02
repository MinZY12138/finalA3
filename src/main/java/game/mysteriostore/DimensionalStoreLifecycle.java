package game.mysteriostore;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.dimensional.DimensionalGround;
import game.grounds.dimensional.StoreLifecycle;

import java.util.Objects;

/**
 * Default lifecycle that links a dimensional ground to a registered store interior.
 */
public final class DimensionalStoreLifecycle implements StoreLifecycle {

    private final DimensionalStoreType type;
    private MysterioStoreSession activeSession;

    public DimensionalStoreLifecycle(DimensionalStoreType type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public void onOpen(DimensionalGround ground, Location location) {
        Objects.requireNonNull(ground, "Dimensional ground is required");
        Objects.requireNonNull(location, "Portal location is required");

        if (activeSession != null && activeSession.isOpen()) {
            return;
        }

        MysterioStoreInterior interior = StoreDirectory.getInterior(type);
        MysterioStoreSession session = interior.openSession(location);
        activeSession = session;
        ground.activateStore(session);
    }

    @Override
    public void onClose(DimensionalGround ground, Location location) {
        Objects.requireNonNull(ground, "Dimensional ground is required");
        Objects.requireNonNull(location, "Portal location is required");

        if (activeSession != null) {
            activeSession.forceClose();
            activeSession = null;
        }

        ground.deactivateStore();
    }
}