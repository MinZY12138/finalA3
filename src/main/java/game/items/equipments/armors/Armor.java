package game.items.equipments.armors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Abilities;

import java.util.List;

/**
 * <h1>Armor class</h1>
 * <p>
 * The {@code Armor} represents a {@link Wearable} {@link Item} that provides defensive capabilities to the
 * {@link Actor}.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public abstract class Armor extends Item implements Wearable
{
    /**
     * The defense value of the armor.
     */
    private final int DEFENSE;

    /**
     * Constructor of the Armor class.
     *
     * @param name        the name of the armor
     * @param displayChar the character of the armor that display on the map
     * @param portable    determines the armor can be picked up
     * @param defense     the defense value of the armor
     */
    public Armor(String name, char displayChar, boolean portable, int defense)
    {
        super(name, displayChar, portable);
        this.DEFENSE = defense;
    }

    /**
     * This method is used to equip this armor onto the given actor.
     *
     * @param actor       the actor who wear the armor
     * @param armorHolder the management of the equipped armor state
     * @return a string message the wear action result
     */
    @Override
    public String wornBy(Actor actor, Wearing armorHolder)
    {
        actor.enableAbility(Abilities.BLOCK_ATTACK);

        // Reset to default abilities.
        actor.disableAbility(Abilities.IMMUNE_STATUSES);
        actor.disableAbility(Abilities.COLD_RESISTANT);
        armorHolder.setArmor(this);
        return actor + " has wear this armor: " + this;
    }

    /**
     * List of allowable actions that can be performed on the item when it is on the ground
     *
     * @param location the location of the ground on which the item lies
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Location location)
    {
        ActionList actionList = super.allowableActions(location);

        if (location.containsAnActor())
        {
            Actor target = location.getActor();
            List<Wearing> armorHolder = target.getItemInventoryAs(Wearing.class);

            if (!armorHolder.isEmpty())
            {
                int firstElement = 0;
                actionList.add(WearActionInjector.createWearAction(
                        this, armorHolder.get(firstElement), target));
            }
        }

        return actionList;
    }

    /**
     * The getter of this armor's defense value.
     *
     * @return a string message with defense value
     */
    public String getFunctionality()
    {
        return "can block " + this.DEFENSE + " damage,";
    }

    /**
     * The getter of this armor's defense value.
     *
     * @return defense value
     */
    public int getDEFENSE()
    {
        return DEFENSE;
    }

    /**
     * The getter of the name of the armor.
     *
     * @return armor name
     */
    public String getName()
    {
        return super.toString();
    }

    /**
     * Returns a string description of the armor, including its name, display character and defense value message.
     *
     * @return a formatted string representation of the armor
     */
    @Override
    public String toString()
    {
        return getName() + " " + this.getDisplayChar() + " " +
                this.getFunctionality();
    }
}
