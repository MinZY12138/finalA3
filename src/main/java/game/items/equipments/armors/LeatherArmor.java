package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Abilities;
import game.items.ItemInfo;

/**
 * <h1>LeatherArmor class</h1>
 * <p>
 * The {@code LeatherArmor} is a type of {@link Armor} that can be worn by {@link Actor}.
 * When {@link Actor} wear it, {@link Actor} will resist cold (stop decreasing warmth level).
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public class LeatherArmor extends Armor
{
    /**
     * The ability cold resistant for leather armor.
     */
    private static final Abilities ABILITIES = Abilities.COLD_RESISTANT;

    /**
     * Constructor of the LeatherArmor class.
     */
    public LeatherArmor()
    {
        super(
                ItemInfo.LEATHER_ARMOR.getNAME(),
                ItemInfo.LEATHER_ARMOR.getCHAR(),
                ItemInfo.LEATHER_ARMOR.isPORTABLE(),
                ArmorInfo.LEATHER_ARMOR.getDEFENSE()
        );
    }

    /**
     * Wear this armor onto the actor.
     *
     * @param actor       the actor who wear the armor
     * @param armorHolder the management of the equipped armor state
     * @return a string describing the result of the wear action
     */
    @Override
    public String wornBy(Actor actor, Wearing armorHolder)
    {
        String message = super.wornBy(actor, armorHolder);
        actor.enableAbility(ABILITIES);
        return message;
    }

    /**
     * Getter of a string describing armor's defensive and special capabilities.
     *
     * @return a formatted string describing this armor's effects
     */
    @Override
    public String getFunctionality()
    {
        return super.getFunctionality() + "\n   and has ability " + ABILITIES.name();
    }
}
