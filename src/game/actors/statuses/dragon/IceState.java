package game.actors.statuses.dragon;

import java.util.Random;

public class IceState implements DragonState{

    private final Random random = new Random();
    private static final int FIRE_STATE_CHANCE = 40;
    private static final int DIRT_STATE_CHANCE = 30;


    @Override
    public DragonState getNextState(){
        if (random.nextInt(100) < DIRT_STATE_CHANCE ){
            return new DirtState();
        } else if (random.nextInt(100) > DIRT_STATE_CHANCE + FIRE_STATE_CHANCE)
        {
            return new FireState();
        }
        else{
            return this;
        }

    }

    @Override
    public String getStateName(){
        return "Ice State";
    }
}
