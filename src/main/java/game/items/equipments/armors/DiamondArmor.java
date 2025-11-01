package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Abilities;
import game.items.ItemInfo;

public class DiamondArmor extends Armor
{

    private static final Abilities ABILITIES = Abilities.IMMUNE_STATUSES;

    public DiamondArmor()
    {
        super(
                ItemInfo.DIAMOND_ARMOR.getNAME(),
                ItemInfo.DIAMOND_ARMOR.getCHAR(),
                ItemInfo.DIAMOND_ARMOR.isPORTABLE(),
                ArmorInfo.DIAMOND_DEFENSE.getDEFENSE()
        );
    }

    @Override
    public String wornBy(Actor actor, Wearing armorHolder)
    {
        String message = super.wornBy(actor, armorHolder);
        actor.enableAbility(ABILITIES);
        return message;
    }

    @Override
    public String getFunctionality()
    {
        return super.getFunctionality() + "\n   and has ability " +
                ABILITIES.name();
    }
}
