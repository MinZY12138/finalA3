package game.actors.statuses.dragon;

import java.util.Random;

public class FireState implements DragonState{

    private final Random random = new Random();
    private static final int ICE_STATE_CHANCE = 40;
    private static final int DIRT_STATE_CHANCE = 30;


    @Override
    public DragonState getNextState(){
        if (random.nextInt(100) < ICE_STATE_CHANCE ){
            return new IceState();
        } else if (random.nextInt(100) > DIRT_STATE_CHANCE + ICE_STATE_CHANCE)
        {
            return new DirtState();
        }
        else{
            return this;
        }

    }

    @Override
    public String getStateName(){
        return "Fire State";
    }
}
