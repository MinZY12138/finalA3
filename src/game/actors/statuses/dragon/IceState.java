package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

import java.util.Random;

public class IceState implements DragonState{

    private final Random random = new Random();
    private static final int FIRE_STATE_CHANCE = 40;
    private static final int DIRT_STATE_CHANCE = 30;
    private static final int RANDOM_BOUND = 100;
    private static final int DURATION = 8;


    @Override
    public DragonState getNextState(){
        int chance  = random.nextInt(RANDOM_BOUND);
        if (chance < DIRT_STATE_CHANCE ){
            return new DirtState();
        } else if (chance < DIRT_STATE_CHANCE + FIRE_STATE_CHANCE)
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

    @Override
    public int setStateDuration() {
        return DURATION;
    }

    public void setDragonAction(Animal dragon, GameMap map) {
        dragon.resistanceToWarm = true;
    }
}
