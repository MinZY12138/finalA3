package game.grounds.trees.yewBerrys;

public interface CreateYewBerryTrees
{
    static YewBerryTree createMatureYewBerryTree (){
        return new YewBerryTree();
    }

    static YewBerrySapling createYewBerrySapling (boolean canProduce)
    {
        YewBerrySapling yewBerrySapling = new YewBerrySapling(canProduce);
        yewBerrySapling.setNextStage(createMatureYewBerryTree());
        return yewBerrySapling;
    }
}
