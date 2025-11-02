package game.mysteriostore;

import game.actors.Seller;
import game.mysteriostore.DimensionalStoreType;

/**
 * Template for configuring a predefined Mysterio store.
 */
public interface MysterioStore {

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