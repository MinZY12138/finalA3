package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.ItemInfo;

/**
 * <h1>ArmorHolder class</h1>
 * <p>
 * The {@code ArmorHolder} class responsible for managing an {@link Armor} worn by an {@link Actor}.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public class ArmorHolder extends Item implements Wearing
{
    /**
     * The armor currently wear by the actor.
     */
    private Armor armor;

    /**
     * The actor who wear this armor.
     */
    private final Actor ACTOR;

    /**
     * Constructor of the ArmorHolder class.
     *
     * @param actor the actor who wear this armor
     */
    public ArmorHolder(Actor actor)
    {
        super(ItemInfo.ARMOR_HOLDER.getNAME(),
                ItemInfo.ARMOR_HOLDER.getCHAR(),
                ItemInfo.ARMOR_HOLDER.isPORTABLE());
        this.ACTOR = actor;
    }

    /**
     * Setter to set the given armor to the actor.
     *
     * @param armor the armor that wear by the actor
     * @return a string message that an actor successfully wear the given armor
     */
    @Override
    public String setArmor(Armor armor)
    {
        this.armor = armor;
        return ACTOR + " has wear " + armor;
    }

    /**
     * Getter to get this armor defense value.
     *
     * @return this armor defense value
     */
    @Override
    public int getBlockArmor()
    {
        return this.armor.getDEFENSE();
    }

    /**
     * Getter to get the armor name and its functionality.
     *
     * @return a string description for the equipped armor
     */
    @Override
    public String getSimpleArmorInfo()
    {
        return armor.getName() + " " + armor.getFunctionality();
    }

    /**
     * Getter of the detailed string description of the equipped armor.
     *
     * @return a string description of the equipped armor
     */
    @Override
    public String getArmorInfo()
    {
        String returnString = "No armor yet";

        if (armor != null)
        {
            returnString = armor.toString();
        }

        return returnString;
    }
}
