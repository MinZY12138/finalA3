package game.grounds.trees.apples;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.Growthable;

/**
 * <h1>Abstract class `AppleChild`</h1>
 * <p>
 * Represents the base class for Apple tree types.
 * This class responsible for managing Apple tree types specific logic,
 * (e.g., summonFruit) and the creation of all Apple tree stages
 * including mature form.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public abstract class AppleChild extends Growthable implements SummonApple, CreateAppleTrees
{
    /**
     * The percentage that success to transform into next stage (int).
     */
    private static final int TRANSFORM_RATE = 100;

    /**
     * Constructor for AppleChild
     *
     * @param displayChar         a character represent this object.
     * @param name                name of this object
     * @param turnsToProduceFruit the turn that this object to produce a fruit
     * @param turnToGrowth        the turn that indicate it is the time to growth into next stage
     * @param CAN_PRODUCE         indicate if this object can produce fruit or not.
     */
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

    /**
     * Class method to return a new instance of normal AppleSprouts
     *
     * @param canProduce          can this sprouts produce fruit
     * @param nextStageCanProduce can the next stage (sapling) can produce fruit
     * @return a new instance {@link AppleSprouts}
     */
    public static AppleSprouts createAppleSprouts(boolean canProduce, boolean nextStageCanProduce)
    {
        return CreateAppleTrees.createAppleSprouts(canProduce, nextStageCanProduce);
    }

    /**
     * Class method to return a new instance of apple sprouts that skip sapling stage.
     *
     * @param canProduce can this sprouts produce fruit
     * @return a new instance {@link AppleSprouts}
     */
    public static AppleSprouts createSkipSaplingAppleSprouts(boolean canProduce)
    {
        return CreateAppleTrees.createSkipSaplingAppleSprouts(canProduce);
    }

    /**
     * Class method to create a new AppleSapling object.
     *
     * @param canProduce can this sapling produce fruit.
     * @return a new instance {@link AppleSapling}
     */
    public static AppleSapling createAppleSapling(boolean canProduce)
    {
        return CreateAppleTrees.createAppleSaplingTree(canProduce);
    }

    /**
     * Class method to create a new instance of AppleTree (mature stage)
     *
     * @return a new instance of {@link AppleTree}
     */
    public static AppleTree createMatureAppleTree()
    {
        return CreateAppleTrees.createMatureAppleTree();
    }
}
