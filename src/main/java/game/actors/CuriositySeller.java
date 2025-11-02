package game.actors;

import edu.monash.fit2099.engine.items.Item;
import game.actors.statuses.StatusType;
import game.items.equipments.weapons.Axe;
import game.items.equipments.weapons.Bow;
import game.items.equipments.weapons.Torch;
import game.items.equipments.weapons.WeaponType;
import game.mysteriostore.Merchandise;
import game.mysteriostore.RandomPriceApi;

import java.util.List;
import java.util.function.Supplier;

/**
 * Seller providing weaponry curiosities.
 */
public final class CuriositySeller extends Seller {

    public CuriositySeller(RandomPriceApi priceApi) {
        super(
                "Myst",
                'M',
                List.of(
                        new Merchandise(
                                "Battle Axe",
                                new Supplier<Item>() {
                                    @Override public Item get() {
                                        return new Axe(WeaponType.AXE, StatusType.BLEEDING);
                                    }
                                },
                                priceApi.randomPrice(2, 3, 1, 2, 0, 0)
                        ),
                        new Merchandise(
                                "Hunting Bow",
                                new Supplier<Item>() {
                                    @Override public Item get() {
                                        return new Bow(WeaponType.BOW, null);
                                    }
                                },
                                priceApi.randomPrice(0, 1, 2, 3, 0, 0)
                        ),
                        new Merchandise(
                                "Blazing Torch",
                                new Supplier<Item>() {
                                    @Override public Item get() {
                                        return new Torch(WeaponType.TORCH, StatusType.BURNING);
                                    }
                                },
                                priceApi.randomPrice(1, 2, 1, 2, 0, 0)
                        )
                )
        );
    }
}
