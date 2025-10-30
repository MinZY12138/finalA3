package game.grounds.trees.apples;

import game.grounds.GroundInfo;

/**
 * <h1>Concrete class `AppleSapling`</h1>
 * <p>
 * Represent a sapling stage of an apple tree in
 * this system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class AppleSapling extends AppleChild
{
    /**
     * Indicate the turn to produce a fruit.
     */
    private static final int TURN_TO_PRODUCE_FRUIT = 2;

    /**
     * Indicate the after how many turn it growth into next stage.
     */
    private static final int TURN_TO_GROWTH = 5;

    /**
     * Constructor for AppleSapling
     *
     * @param CAN_PRODUCE can this sapling produce fruit.
     */
    public AppleSapling(boolean CAN_PRODUCE)
    {
        super(GroundInfo.APPLE_SAPLING.getDISPLAY_CHAR(),
                GroundInfo.APPLE_SAPLING.getNAME(),
                TURN_TO_PRODUCE_FRUIT,
                TURN_TO_GROWTH,
                CAN_PRODUCE
        );
    }
}
