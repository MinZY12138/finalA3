package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.*;
import game.items.fruits.*;

/**
 * Consume action
 * @author Ng Jun Jie
 * @version 1.0
 */
public class ConsumeAction extends Action
{

    public final Item item;

    /**
     * Constructor for consume action
     * @param item consumable item
     */
    public ConsumeAction(Item item)
    {

        this.item = item;

    }


    @Override
    public String execute(Actor actor, GameMap map)
    {
        Location place = map.locationOf(actor);

        if (place == null){
            return "";
        }


        String result = actor + " consumes " + item;

        if (item instanceof Consumable consumable)
        {
            result +=  consumable.consume(actor, map);
        }

        if (actor.getItemInventory().contains(item)){
            actor.removeItemFromInventory(item);
        }
        else {
            place.removeItem(item);
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor)
    {
        return actor + " consumes " + item;
    }

}

