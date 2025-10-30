package game.items.fruits;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actions.*;
import game.actions.*;

/**
 *
 * <p>
 *     Abstract class representing a fruit.
 *     Fruits are consumable items that can be eaten by actors.
 *     Provides default {@link #allowableActions} for actors carrying the fruit.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public abstract class Fruit extends Item implements Consumable
{

    /**
     * Constructor for fruit
     * @param name name of fruit
     * @param displayChar display character of fruit
     * @param portable whether the fruit is portable
     */
    public Fruit(String name, char displayChar, boolean portable )
    {
        super(name, displayChar, portable);
        this.enableAbility(Food.CONSUME);


    }

    /**
     * provide allowable actions for the fruits
     * @param actor the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return action list
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map)
    {
        ActionList actions = new ActionList();
        if (actor.getItemInventory().contains(this))
        {
            actions.add(new ConsumeAction(this));
        }

        return actions;
    }

}
