package game.capabilities;

import game.grounds.Dirt;

/**
 * <h1>Interface SummonDirt</h1>
 *
 * Act as an injector to inject Dirt.
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface SummonDirt
{
    /**
     * Method to return a new Dirt
     * @return {@link Dirt} object
     */
    default Dirt summonDirt()
    {
        return new Dirt();
    }
}
