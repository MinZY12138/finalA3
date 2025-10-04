package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for items that can be consumed by actors.
 */
public interface Consumable
{
    /**
     * Consumes the item.
     *
     * @param consumer the actor consuming this item
     * @param map the game map where the consumption occurs
     * @return description of the consumption result
     */
    String consume(Actor consumer, GameMap map);
}
