package game.grounds.trees.yewBerrys;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundInfo;
import game.grounds.trees.Growthable;

public class YewBerrySapling extends Growthable implements SummonYewBerry
{
    private static final int TRANSFORM_RATE = 50;
    private static final int TURN_TO_PRODUCE_FRUIT = 2;
    private static final int TURN_TO_GROWTH = 3;
    /**
     * Constructor for Growthable
     *
     * @param CAN_PRODUCE         indicate this stage of tree can produce fruit or not.
     */
    public YewBerrySapling(boolean CAN_PRODUCE)
    {
        super(
                GroundInfo.YEW_BERRY_SAPLING.getDISPLAY_CHAR(),
                GroundInfo.YEW_BERRY_SAPLING.getNAME(),
                TURN_TO_PRODUCE_FRUIT,
                TURN_TO_GROWTH,
                CAN_PRODUCE
        );
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
        location.addItem(this.summonYewBerry());
    }
}
