package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Interface Consumable</h1>
 *
 * <p>
 *     Implementing classes define specific effects that occur
 *     when an actor consumes the item, such as restoring health
 *     or applying status effects.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public interface Consumable
{
    /**
     * Consumes the item.
     *
     * @param consumer the actor consuming this item
     * @return description of the consumption result
     */
    String consume(Actor consumer);
}
