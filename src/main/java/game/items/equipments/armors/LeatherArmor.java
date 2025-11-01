package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Abilities;
import game.items.ItemInfo;

public class LeatherArmor extends Armor
{

    private static final Abilities ABILITIES = Abilities.COLD_RESISTANT;

    public LeatherArmor()
    {
        super(
                ItemInfo.LEATHER_ARMOR.getNAME(),
                ItemInfo.LEATHER_ARMOR.getCHAR(),
                ItemInfo.LEATHER_ARMOR.isPORTABLE(),
                ArmorInfo.LEATHER_ARMOR.getDEFENSE()
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
        return super.getFunctionality() +
                "\n   and has ability " + ABILITIES.name();
    }
}
