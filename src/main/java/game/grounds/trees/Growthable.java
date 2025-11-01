package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * <h1>Class represent Growthable</h1>
 *
 * <p>
 * Represent the growth system of tree.
 * </p>
 * <p>
 * Extends from {@link ProduceableFruitTree}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public abstract class Growthable extends ProduceableFruitTree
{
    /**
     * Tree growth time.
     */
    private int turnToGrowth;

    /**
     * Determines this tree can produce fruits.
     */
    private final boolean CAN_PRODUCE;

    /**
     * Next stage of tree.
     */
    private ProduceableFruitTree nextStage;

    /**
     * Rate of tree growth.
     */
    private int transformRate;

    /**
     * Value reset.
     */
    private final int CONSTANT_RESET;

    /**
     * Random object to control random events.
     */
    private static final Random RAND = new Random();

    /**
     * Full successfully rate
     */
    private static final int MAXIMUM_TRANSFORM_BOUND = 100;

    /**
     * Constructor for Growthable.
     *
     * @param displayChar         character representation of the tree
     * @param name                of the tree (e,g., Apple etc...)
     * @param turnsToProduceFruit how many turns will cause this tree to summon a fruit.
     * @param CAN_PRODUCE         indicate this stage of tree can produce fruit or not.
     * @param turnToGrowth        indicate how many turn for this stage of tree to growth.
     */
    public Growthable(char displayChar, String name, int turnsToProduceFruit, int turnToGrowth, boolean CAN_PRODUCE)
    {
        super(displayChar, name, turnsToProduceFruit);
        this.turnToGrowth = turnToGrowth;
        this.CAN_PRODUCE = CAN_PRODUCE;
        this.CONSTANT_RESET = turnToGrowth;
    }

    /**
     * Ground can also experience the joy of time.
     * Each tick will decrement the number of turn to spawn
     * once reaches 0 it will spawn a fruit to its random surrounding.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location)
    {
        if (turnToGrowth == LOWER_BOUND)
        {
            int chancesToTransform = RAND.nextInt(MAXIMUM_TRANSFORM_BOUND);

            if (chancesToTransform < transformRate)
            {
                location.setGround(nextStage);
            } else
            {
                turnToGrowth += CONSTANT_RESET;
                if(CAN_PRODUCE)
                {
                    super.tick(location);
                }
            }
        } else if (CAN_PRODUCE)
        {
            super.tick(location);
        }
        turnToGrowth--;
    }

    /**
     * Set up the next stage of the tree.
     *
     * @param nextStage next stage of tree
     */
    public void setNextStage(ProduceableFruitTree nextStage)
    {
        this.nextStage = nextStage;
    }

    /**
     * Set the rate of the tree transitioning to the next stage.
     *
     * @param transformRate rate of tree growth
     */
    public void setTransformRate(int transformRate)
    {
        this.transformRate = transformRate;
    }
}
