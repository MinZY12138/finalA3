package game.mysteriostore;

import game.actors.Seller;
import game.grounds.dimensional.DimensionalStoreType;

/**
 * Template for configuring a predefined Mysterio store.
 */
public interface MysterioStoreTemplate {

    /**
     * @return the type of store represented by this template
     */
    DimensionalStoreType getType();

    /**
     * Creates the seller assigned to this store template.
     *
     * @return configured seller instance for the store
     */
    Seller createSeller();
}
