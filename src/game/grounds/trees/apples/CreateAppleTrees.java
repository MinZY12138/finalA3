package game.grounds.trees.apples;

public interface CreateAppleTrees
{
    static AppleTree createMatureAppleTree()
    {
        return new AppleTree();
    }

    static AppleSapling createAppleSaplingTree (boolean canProduce)
    {
        AppleSapling appleSapling = new AppleSapling(canProduce);
        appleSapling.setNextStage(createMatureAppleTree());
        return appleSapling;
    }

    static AppleSprouts createAppleSprouts(boolean canProduce, boolean nextStageCanProduce)
    {
        AppleSprouts appleSprouts = new AppleSprouts(canProduce);
        appleSprouts.setNextStage(createAppleSaplingTree(nextStageCanProduce));
        return appleSprouts;
    }

    static AppleSprouts createSkipSaplingAppleSprouts(boolean canProduce)
    {
        AppleSprouts skipSaplingAppleSprouts = new AppleSprouts(canProduce);
        skipSaplingAppleSprouts.setNextStage(createMatureAppleTree());
        return skipSaplingAppleSprouts;
    }
}
