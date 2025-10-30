package game.grounds.trees.yewBerrys;

import game.items.fruits.YewBerry;

/**
 * <h1>Injector interface `SummonYewBerry`</h1>
 * <p>
 * Represent an injector to inject a new instance
 * of YewBerry into the system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface SummonYewBerry
{
    /**
     * Default method to return a new instance of YewBerry object
     *
     * @return a new {@link YewBerry} object.
     */
    default YewBerry summonYewBerry()
    {
        return new YewBerry();
    }
}
