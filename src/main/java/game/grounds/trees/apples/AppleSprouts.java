package game.grounds.trees.apples;

import game.grounds.GroundInfo;

/**
 * <h1>Concrete class `AppleSprouts`</h1>
 * <p>
 * Represent a sprouts stage of an apple tree.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class AppleSprouts extends AppleChild
{
    /**
     * Indicate the turn to produce a fruit.
     */
    private static final int TURN_TO_PRODUCE_FRUIT = 1;

    /**
     * Indicate the after how many turn it growth into next stage.
     */
    private static final int TURN_TO_GROWTH = 3;

    /**
     * Constructor for AppleSprouts
     *
     * @param CAN_PRODUCE can this sprouts produce fruit.
     */
    public AppleSprouts(boolean CAN_PRODUCE)
    {
        super(GroundInfo.APPLE_SPROUTS.getDISPLAY_CHAR(),
                GroundInfo.APPLE_SPROUTS.getNAME(),
                TURN_TO_PRODUCE_FRUIT,
                TURN_TO_GROWTH,
                CAN_PRODUCE
        );
    }
}
