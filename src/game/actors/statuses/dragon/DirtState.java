package game.actors.statuses.dragon;

import java.util.Random;

public class DirtState implements DragonState{

    private final Random random = new Random();
    private static final int FIRE_STATE_CHANCE = 40;
    private static final int ICE_STATE_CHANCE = 30;


    @Override
    public DragonState getNextState(){
        if (random.nextInt(100) < ICE_STATE_CHANCE ){
            return new IceState();
        } else if (random.nextInt(100) > ICE_STATE_CHANCE + FIRE_STATE_CHANCE)
        {
            return new FireState();
        }
        else{
            return this;
        }

    }

    @Override
    public String getStateName(){
        return "Dirt State";
    }
}
