package game.grounds.trees.yewBerrys;

import game.grounds.GroundInfo;

/**
 * <h1>Concrete class `YewBerrySapling`</h1>
 * <p>
 * Represent a sapling stage of a yew berry tree in
 * this system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class YewBerrySapling extends YewBerryChild
{
    /**
     * Rate of this stage of yew berry tree will be transformed
     */
    private static final int TRANSFORM_RATE = 50;

    /**
     * Indicate the turn to produce a fruit.
     */
    private static final int TURN_TO_PRODUCE_FRUIT = 2;

    /**
     * Indicate the after how many turn it growth into next stage.
     */
    private static final int TURN_TO_GROWTH = 2;

    /**
     * Constructor for YewBerrySapling.
     *
     * @param CAN_PRODUCE indicate this stage of tree can produce fruit or not.
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
