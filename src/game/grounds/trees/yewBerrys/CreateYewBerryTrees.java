package game.grounds.trees.yewBerrys;

public interface CreateYewBerryTrees
{
    static YewBerryTree createMatureYewBerryTree (){
        return new YewBerryTree();
    }

    static YewBerrySapling createYewBerrySapling (boolean canProduce)
    {
        return new YewBerrySapling(canProduce);
    }
}
