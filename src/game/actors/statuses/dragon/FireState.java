package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;
import game.behaviours.SplashBehaviour;
import game.capabilities.SummonFire;

import java.util.Random;

public class FireState implements DragonState, SummonFire {

    private final Random random = new Random();
    private static final int ICE_STATE_CHANCE = 40;
    private static final int DIRT_STATE_CHANCE = 30;
    private static final int RANDOM_BOUND = 100;
    private static final int DURATION = 6;


    @Override
    public DragonState getNextState(){
        int chance  = random.nextInt(RANDOM_BOUND);
        if (chance < ICE_STATE_CHANCE ){
            return new IceState();
        } else if (chance < DIRT_STATE_CHANCE + ICE_STATE_CHANCE)
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

    @Override
    public int setStateDuration() {
        return DURATION;
    }

    public void setDragonAction(Animal dragon, GameMap map) {
        dragon.resistanceToWarm = false;
        int HIGHEST_PRIORITY = 0;
        //Game rule attack has the most priority in the system.
        dragon.addBehaviourToAnimal(new SplashBehaviour(), HIGHEST_PRIORITY);

    }
}
