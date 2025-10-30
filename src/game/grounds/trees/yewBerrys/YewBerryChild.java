package game.grounds.trees.yewBerrys;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.Growthable;

/**
 * <h1>Abstract class `YewBerryChild`</h1>
 * <p>
 *     Class to manage yew berry type.
 *     It responsible to handle all yew berry type
 *     and creation including mature stage.
 * </p>
 */
public abstract class YewBerryChild extends Growthable implements SummonYewBerry, CreateYewBerryTrees
{
    /**
     * Constructor for YewBerryChild
     * @param displayChar
     * @param name
     * @param turnsToProduceFruit
     * @param turnToGrowth
     * @param CAN_PRODUCE
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
     *
     * @param canProduce
     * @return
     */
    public static YewBerrySapling createYewBerrySapling (boolean canProduce)
    {
        return CreateYewBerryTrees.createYewBerrySapling(canProduce);
    }

    public static YewBerryTree createMatureYewBerryTree ()
    {
        return CreateYewBerryTrees.createMatureYewBerryTree();
    }
}
