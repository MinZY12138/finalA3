package game.grounds.trees.yewBerrys;

import game.items.fruits.YewBerry;

public interface SummonYewBerry
{
    default YewBerry summonYewBerry (){
        return new YewBerry();
    }
}
