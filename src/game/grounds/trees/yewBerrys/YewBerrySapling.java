package game.grounds.trees.yewBerrys;

import game.grounds.GroundInfo;

public class YewBerrySapling extends YewBerryChild
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
}
