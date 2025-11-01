package game.items.equipments.armors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Abilities;

import java.util.List;

import static game.actors.Abilities.BLOCK_ATTACK;

public abstract class Armor extends Item implements Wearable
{

    private final int DEFENSE;

    public Armor(String name, char displayChar, boolean portable, int defense)
    {
        super(name, displayChar, portable);
        this.DEFENSE = defense;
    }

    @Override
    public String wornBy(Actor actor, Wearing armorHolder)
    {
        actor.enableAbility(BLOCK_ATTACK);

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

    public String getFunctionality()
    {
        return "can block " + this.DEFENSE + " damage,";
    }

    public int getDEFENSE()
    {
        return DEFENSE;
    }

    public String getName()
    {
        return super.toString();
    }

    @Override
    public String toString()
    {
        String returnString = getName() + " " + this.getDisplayChar() + " " +
                this.getFunctionality();
        return returnString;
    }
}
