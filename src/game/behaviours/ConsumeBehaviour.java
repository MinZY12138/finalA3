package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.*;
import edu.monash.fit2099.engine.items.*;
import game.actions.ConsumeAction;
import game.items.fruits.Consumable;


public class ConsumeBehaviour implements Behaviour {

    @Override
    public Action generateAction(Actor actor, GameMap map) {



        Location place = map.locationOf(actor);

        for (Item item : place.getItems()){

            if (item instanceof Consumable){

                return new ConsumeAction(item);
            }
        }
        return null;
    }
}
