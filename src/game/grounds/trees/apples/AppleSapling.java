package game.grounds.trees.apples;

import game.grounds.GroundInfo;

public class AppleSapling extends AppleChild
{
    private static final int TURN_TO_PRODUCE_FRUIT = 2;
    private static final int TURN_TO_GROWTH = 5;
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
