package game.mysteriostore;

import game.actors.Seller;
import game.actors.statuses.StatusType;
import game.grounds.dimensional.DimensionalStoreType;
import game.items.currency.BlueDiamond;
import game.items.currency.GreenDiamond;
import game.items.equipments.weapons.Axe;
import game.items.equipments.weapons.Bow;
import game.items.equipments.weapons.Torch;
import game.items.equipments.weapons.WeaponType;

import java.util.List;

/**
 * Predefined curiosity store template.
 */
final class CuriosityStoreTemplate implements MysterioStoreTemplate {

    CuriosityStoreTemplate() {
    }

    @Override
    public DimensionalStoreType getType() {
        return DimensionalStoreType.CURIOSITY;
    }

    @Override
    public Seller createSeller() {
        return new Seller(
                "Myst",
                'M',
                List.of(
                        new Merchandise(
                                "Battle Axe",
                                () -> new Axe(WeaponType.AXE, StatusType.BLEEDING),
                                Price.builder().add(GreenDiamond.class, 2).add(BlueDiamond.class, 1).build()
                        ),
                        new Merchandise(
                                "Hunting Bow",
                                () -> new Bow(WeaponType.BOW, null),
                                Price.builder().add(BlueDiamond.class, 2).build()
                        ),
                        new Merchandise(
                                "Blazing Torch",
                                () -> new Torch(WeaponType.TORCH, StatusType.BURNING),
                                Price.builder().add(GreenDiamond.class, 1).add(BlueDiamond.class, 1).build()
                        )
                ),
                List.of()
        );
    }
}
