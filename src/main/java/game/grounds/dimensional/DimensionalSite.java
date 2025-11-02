package game.grounds.dimensional;

import game.mysteriostore.DimensionalStoreType;

/**
 * Marker interface for grounds affected by dimensional energy.
 */
public interface DimensionalSite {

    /**
     * @return the store type associated with this dimensional site.
     */
    DimensionalStoreType getStoreType();
}