package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Abilities;
import game.items.ItemInfo;

/**
 * <h1>DiamondArmor class</h1>
 * <p>
 * The {@code DiamondArmor} is a type of {@link Armor} that can be worn by {@link Actor}.
 * When {@link Actor} wear it, {@link Actor} will immune from all statuses (continuous damage).
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public class DiamondArmor extends Armor
{
    /**
     * The ability of immune all statuses for diamond armor.
     */
    private static final Abilities ABILITIES = Abilities.IMMUNE_STATUSES;

    /**
     * Constructor of the DiamondArmor class.
     */
    public DiamondArmor()
    {
        super(
                ItemInfo.DIAMOND_ARMOR.getNAME(),
                ItemInfo.DIAMOND_ARMOR.getCHAR(),
                ItemInfo.DIAMOND_ARMOR.isPORTABLE(),
                ArmorInfo.DIAMOND_ARMOR.getDEFENSE()
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
