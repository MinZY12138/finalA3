package game.grounds.trees.apples;

import game.items.fruits.Apple;

/**
 * <h1>Injector interface `SummonApple`</h1>
 * <p>
 * Represent an injector to inject a new instance
 * of Apple into the system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface SummonApple
{
    /**
     * Static method to return a new instance of Apple object
     *
     * @return a new {@link Apple} object.
     */
    static Apple summonApple()
    {
        return new Apple();
    }
}
