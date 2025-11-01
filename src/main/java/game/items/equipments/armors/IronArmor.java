package game.items.equipments.armors;

import game.items.ItemInfo;

public class IronArmor extends Armor {
    public IronArmor() {
        super(
                ItemInfo.IRON_ARMOR.getNAME(),
                ItemInfo.IRON_ARMOR.getCHAR(),
                ItemInfo.IRON_ARMOR.isPORTABLE(),
                ArmorInfo.IRON_DEFENSE.getDEFENSE()
        );
    }
}
