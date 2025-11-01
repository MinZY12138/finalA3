package game.grounds.dimensional;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Lifecycle hooks invoked when a dimensional store manifests or collapses.
 */
public interface StoreLifecycle {

    StoreLifecycle NONE = new StoreLifecycle() {
        @Override
        public void onOpen(DimensionalGround ground, Location location) {
        }

        @Override
        public void onClose(DimensionalGround ground, Location location) {
        }
    };

    /**
     * Called when the store emerges from the dimensional ground.
     *
     * @param ground   the dimensional ground triggering the event
     * @param location the location where the store should appear
     */
    void onOpen(DimensionalGround ground, Location location);

    /**
     * Called when the store collapses and the ground should revert.
     *
     * @param ground   the dimensional ground triggering the event
     * @param location the location where the store existed
     */
    void onClose(DimensionalGround ground, Location location);
}