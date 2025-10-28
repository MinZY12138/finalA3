package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.Apple;

/**
 * <h1>Class represent AppleTree</h1>
 *
 * <p>
 *     Represent an Apple tree in this system.
 * </p>
 *
 * Extends from {@link ProduceableFruitTree}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class AppleTree extends ProduceableFruitTree
{
    /**
     * Constructor for AppleTree
     */
    public AppleTree()
    {
        //Pass its parameter to its parent's constructor.
        super('T', "Apple Tree",3);
    }

    /**
     * Method to summon a fruits on a specific location.
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        //Drop an Apple object on to the location.
        location.addItem(new Apple());
    }
}
