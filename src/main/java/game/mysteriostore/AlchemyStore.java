package game.mysteriostore;

import game.actors.Seller;
import game.mysteriostore.DimensionalStoreType;
import game.items.currency.BlueDiamond;
import game.items.currency.GreenDiamond;
import game.items.fruits.Apple;
import game.items.fruits.Hazelnut;
import game.items.fruits.YewBerry;

import java.util.List;

/**
 * Predefined alchemy store template.
 */
final class AlchemyStore implements MysterioStore {

    AlchemyStore() {
    }

    @Override
    public DimensionalStoreType getType() {
        return DimensionalStoreType.ALCHEMY;
    }

    @Override
    public Seller createSeller() {
        return new Seller(
                "Aurora",
                'α',
                List.of(
                        new Merchandise("Apple", Apple::new, Price.builder().add(GreenDiamond.class, 1).build()),
                        new Merchandise("Hazelnut", Hazelnut::new, Price.builder().add(GreenDiamond.class, 2).build()),
                        new Merchandise("Yew Berry", YewBerry::new, Price.builder().add(BlueDiamond.class, 1).build())
                ),
                List.of()
        );
    }
}