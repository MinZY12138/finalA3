package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.*;
import edu.monash.fit2099.engine.items.*;
import game.actions.ConsumeAction;
import game.items.fruits.Consumable;

/**
 * <h1>Class represent ConsumeBehaviour</h1>
 *
 * <p>
 *     A behaviour that allows an {@link Actor} to consume an item at its current location.
 *     This behaviour looks through all {@link Item}s on the ground at the actor’s
 *     location. If any item implements {@link Consumable}, the actor will generate
 *     a {@link ConsumeAction} to eat/consume it.
 * </p>
 *
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class ConsumeBehaviour implements Behaviour {

    /**
     * Searches the actor's current location for consumable items.
     *
     * @param actor the actor whose turn it is
     * @param map   the map the actor is currently on
     * @return a {@link ConsumeAction} if a consumable item exists, otherwise {@code null}
     */
    @Override
    public Action generateAction(Actor actor, GameMap map) {

        Location place = map.locationOf(actor);

        for (Item item : place.getItems()){
            if(Consumable.class.isInstance(item)){

                return new ConsumeAction((Consumable) item);
            }
        }
        return null;
    }
}
