package game.mysteriostore;

import game.actors.Seller;
import game.grounds.dimensional.DimensionalStoreType;
import game.items.currency.BlueDiamond;
import game.items.currency.GreenDiamond;
import game.items.currency.RedDiamond;
import game.items.equipments.armors.DiamondArmor;
import game.items.equipments.armors.IronArmor;
import game.items.equipments.armors.LeatherArmor;

import java.util.List;

/**
 * Predefined armoury store template.
 */
final class ArmouryStoreTemplate implements MysterioStoreTemplate {

    ArmouryStoreTemplate() {
    }

    @Override
    public DimensionalStoreType getType() {
        return DimensionalStoreType.ARMOURY;
    }

    @Override
    public Seller createSeller() {
        return new Seller(
                "Aegis",
                'A',
                List.of(),
                List.of(
                        new ArmourListing("Leather Armour", Price.builder().add(GreenDiamond.class, 3).build(), LeatherArmor::new),
                        new ArmourListing(
                                "Iron Armour",
                                Price.builder().add(GreenDiamond.class, 2).add(BlueDiamond.class, 2).build(),
                                IronArmor::new
                        ),
                        new ArmourListing(
                                "Diamond Armour",
                                Price.builder().add(BlueDiamond.class, 3).add(RedDiamond.class, 1).build(),
                                DiamondArmor::new
                        )
                )
        );
    }
}
