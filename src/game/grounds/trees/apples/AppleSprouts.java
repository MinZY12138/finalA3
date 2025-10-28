package game.grounds.trees.apples;

import game.grounds.GroundInfo;

public class AppleSprouts extends AppleChild
{
    private static final int TURN_TO_PRODUCE_FRUIT = 1;
    private static final int TURN_TO_GROWTH = 3;
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
