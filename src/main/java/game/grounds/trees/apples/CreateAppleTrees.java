package game.grounds.trees.apples;

/**
 * <h1>Interface represent CreateAppleTrees</h1>
 *
 * <p>
 * This interface provide factory static methods to create various growth stages of apple trees.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface CreateAppleTrees
{
    /**
     * Create a mature apple tree instance.
     *
     * @return a mature apple tree
     */
    static AppleTree createMatureAppleTree()
    {
        return new AppleTree();
    }

    /**
     * Create a sapling apple tree instance.
     * Set the next stage of sapling apple tree to mature apple tree.
     *
     * @param canProduce determines this stage of apple tree can produce apple
     * @return a sapling apple tree
     */
    static AppleSapling createAppleSaplingTree(boolean canProduce)
    {
        AppleSapling appleSapling = new AppleSapling(canProduce);
        appleSapling.setNextStage(createMatureAppleTree());
        return appleSapling;
    }

    /**
     * Create a sprouts apple tree instance.
     * Set the next stage of sprouts apple tree to sapling apple tree.
     *
     * @param canProduce          determines this stage of apple tree can produce apple
     * @param nextStageCanProduce determines the next stage of apple tree can produce apple
     * @return a sprouts apple tree
     */
    static AppleSprouts createAppleSprouts(boolean canProduce, boolean nextStageCanProduce)
    {
        AppleSprouts appleSprouts = new AppleSprouts(canProduce);
        appleSprouts.setNextStage(createAppleSaplingTree(nextStageCanProduce));
        return appleSprouts;
    }

    /**
     * Create a sprouts apple tree instance.
     * Set the next stage of sprouts apple tree to mature apple tree (skip sapling stage).
     *
     * @param canProduce determines this stage of apple tree can produce apple
     * @return a sprouts apple tree but skip sapling stage
     */
    static AppleSprouts createSkipSaplingAppleSprouts(boolean canProduce)
    {
        AppleSprouts skipSaplingAppleSprouts = new AppleSprouts(canProduce);
        skipSaplingAppleSprouts.setNextStage(createMatureAppleTree());
        return skipSaplingAppleSprouts;
    }
}
