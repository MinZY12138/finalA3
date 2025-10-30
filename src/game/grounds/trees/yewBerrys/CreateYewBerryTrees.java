package game.grounds.trees.yewBerrys;

/**
 * <h1>Interface represent CreateYewBerryTrees</h1>
 *
 * <p>
 * This interface provide factory static methods to create various growth stages of yew berry trees.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface CreateYewBerryTrees
{
    /**
     * Create a mature yew berry instance.
     *
     * @return a mature yew berry tree
     */
    static YewBerryTree createMatureYewBerryTree()
    {
        return new YewBerryTree();
    }

    /**
     * Create a sapling yew berry tree instance.
     * Set the next stage of sapling yew berry tree to mature yew berry tree.
     *
     * @param canProduce determines this stage of apple tree can produce yew berry
     * @return a sapling yew berry tree
     */
    static YewBerrySapling createYewBerrySapling(boolean canProduce)
    {
        YewBerrySapling yewBerrySapling = new YewBerrySapling(canProduce);
        yewBerrySapling.setNextStage(createMatureYewBerryTree());
        return yewBerrySapling;
    }
}
