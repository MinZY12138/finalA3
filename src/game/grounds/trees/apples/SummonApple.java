package game.grounds.trees.apples;

import game.items.fruits.Apple;

public interface SummonApple
{
    default Apple summonApple(){
        return new Apple();
    }
}
