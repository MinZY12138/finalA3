package game.items.equipments.armors;

import game.items.ItemInfo;

/**
 * <h1>IronArmor class</h1>
 * <p>
 * The {@code IronArmor} is a type of {@link Armor} that can be worn by Actor.
 * This armor don't have specific capabilities.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public class IronArmor extends Armor
{

    /**
     * Constructor of the IronArmor class.
     */
    public IronArmor()
    {
        super(
                ItemInfo.IRON_ARMOR.getNAME(),
                ItemInfo.IRON_ARMOR.getCHAR(),
                ItemInfo.IRON_ARMOR.isPORTABLE(),
                ArmorInfo.IRON_ARMOR.getDEFENSE()
        );
    }
}
