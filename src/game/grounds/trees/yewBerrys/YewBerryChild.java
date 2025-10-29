package game.grounds.trees.yewBerrys;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.Growthable;

public class YewBerryChild extends Growthable implements SummonYewBerry, CreateYewBerryTrees
{
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

    public static YewBerrySapling createYewBerrySapling (boolean canProduce)
    {
        return CreateYewBerryTrees.createYewBerrySapling(canProduce);
    }

    public static YewBerryTree createMatureYewBerryTree ()
    {
        return CreateYewBerryTrees.createMatureYewBerryTree();
    }
}
