package game.actors;


import game.items.fruits.Apple;
import game.items.fruits.Hazelnut;
import game.items.fruits.YewBerry;
import game.mysteriostore.Merchandise;
import game.mysteriostore.RandomPriceApi;

import java.util.List;

/**
 * Seller responsible for the alchemy merchandise selection.
 */
public final class AlchemySeller extends Seller {

    /**
     * Creates the alchemy-themed seller with fruit-based merchandise.
     */
    public AlchemySeller(RandomPriceApi priceApi) {
        super(
                "Aurora",
                'α',
                List.of(
                        new Merchandise(
                                "Apple",
                                Apple::new,
                                priceApi.randomPrice(1, 2, 0, 0, 0, 0)
                        ),
                        new Merchandise(
                                "Hazelnut",
                                Hazelnut::new,
                                priceApi.randomPrice(2, 3, 0, 1, 0, 0)
                        ),
                        new Merchandise(
                                "Yew Berry",
                                YewBerry::new,
                                priceApi.randomPrice(0, 0, 1, 2, 0, 0))
                )
        );
    }
}