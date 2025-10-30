package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.*;
import game.items.fruits.*;

/**
 * <h1>Class represent ConsumeAction</h1>
 *
 * <p>
 * When executed, removes the item from the actor's inventory or the ground
 * and applies the effects defined in the item's {@link Consumable consume} method.
 * </p>
 * @author Ng Jun Jie
 * @version 2.0
 */
public class ConsumeAction extends Action
{

    /**
     * The item used to consume actor
     */
    public final Consumable item;

    /**
     * Constructor for consume action
     * @param item consumable item
     */
    public ConsumeAction(Consumable item)
    {

        this.item = item;

    }


    /**
     * Executes the consume action.
     * Removes the item from inventory or ground and triggers its consume behavior.
     *
     * @param actor the actor performing the action
     * @param map the game map where the action takes place
     * @return a description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        Location place = map.locationOf(actor);

        if (actor.getItemInventory().contains((Item)item))
        {
            actor.removeItemFromInventory((Item)item);
        }
        else {
            place.removeItem((Item)item);
        }

        return actor + " consumes " + item + item.consume(actor);
    }

    /**
     * Menu description for the action.
     *
     * @param actor the actor performing the action
     * @return a string describing this action for the menu
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor + " consumes " + item;
    }

}

