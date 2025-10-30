package game.grounds.trees.yewBerrys;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.Growthable;

/**
 * <h1>Abstract class `YewBerryChild`</h1>
 * <p>
 * Represents the base class for Yew Berry tree types.
 * This class is responsible for managing Yew Berry tree type specific behaviour,
 * (e.g., fruit production `summonFruit`) and creation of all Yew
 * Berry tree stages including mature form.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public abstract class YewBerryChild extends Growthable implements SummonYewBerry, CreateYewBerryTrees
{
    /**
     * Constructor for YewBerryChild
     *
     * @param displayChar         a character represent this object.
     * @param name                name of this object
     * @param turnsToProduceFruit the turn that this object to produce a fruit
     * @param turnToGrowth        the turn that indicate it is the time to growth into next stage
     * @param CAN_PRODUCE         indicate if this object can produce fruit or not.
     */
    public YewBerryChild(char displayChar, String name, int turnsToProduceFruit, int turnToGrowth, boolean CAN_PRODUCE)
    {
        super(displayChar, name, turnsToProduceFruit, turnToGrowth, CAN_PRODUCE);
    }

    /**
     * Method to summon a fruits on a specific location.
     *
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        location.addItem(this.summonYewBerry());
    }

    /**
     * Class method to return a new instance of YewBerrySapling.
     *
     * @param canProduce determines this stage of yew berry tree can produce yew berry
     * @return a new instance {@link YewBerrySapling}
     */
    public static YewBerrySapling createYewBerrySapling(boolean canProduce)
    {
        return CreateYewBerryTrees.createYewBerrySapling(canProduce);
    }

    /**
     * Class method to return a new instance of YewBerryTree.
     *
     * @return a new instance {@link YewBerryTree}
     */
    public static YewBerryTree createMatureYewBerryTree()
    {
        return CreateYewBerryTrees.createMatureYewBerryTree();
    }
}
