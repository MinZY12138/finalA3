package game.grounds.trees.apples;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.Growthable;

public class AppleChild extends Growthable implements SummonApple
{
    private static final int TRANSFORM_RATE = 100;
    public AppleChild(char displayChar, String name, int turnsToProduceFruit, int turnToGrowth, boolean CAN_PRODUCE)
    {
        super(displayChar, name, turnsToProduceFruit, turnToGrowth, CAN_PRODUCE);
        setTransformRate(TRANSFORM_RATE);
    }

    /**
     * Method to summon a fruits on a specific location.
     *
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        location.addItem(this.summonApple());
    }
}
