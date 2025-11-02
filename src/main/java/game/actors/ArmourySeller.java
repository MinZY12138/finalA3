package game.actors;


import game.items.equipments.armors.DiamondArmor;
import game.items.equipments.armors.IronArmor;
import game.items.equipments.armors.LeatherArmor;
import game.mysteriostore.Merchandise;
import game.mysteriostore.RandomPriceApi;

import java.util.List;

/**
 * Seller dedicated to armour merchandise offerings.
 */
public final class ArmourySeller extends Seller {

    /**
     * Creates the armour-focused seller with armour merchandise.
     */
    public ArmourySeller(RandomPriceApi priceApi) {
        super(
                "Aegis",
                'A',
                List.of(
                        new Merchandise(
                                "Leather Armour",
                                LeatherArmor::new,
                                priceApi.randomPrice(3, 5, 0, 0, 0, 0)
                        ),
                        new Merchandise(
                                "Iron Armour",
                                IronArmor::new,
                                priceApi.randomPrice(2, 3, 2, 3, 0, 1)
                        ),
                        new Merchandise(
                                "Diamond Armour",
                                DiamondArmor::new,
                                priceApi.randomPrice(0, 1, 3, 4, 1, 2)
                        )
                )
        );
    }
}
