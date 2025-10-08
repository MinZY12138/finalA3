package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.grounds.Dirt;
import game.grounds.Snow;

import java.util.List;
import java.util.Random;

public class DirtState implements DragonState{

    private final Random random = new Random();
    private static final int FIRE_STATE_CHANCE = 40;
    private static final int ICE_STATE_CHANCE = 30;
    private static final int RANDOM_BOUND = 100;
    private static final int DURATION = 10;


    @Override
    public DragonState getNextState(){
        int chance  = random.nextInt(RANDOM_BOUND);
        if (chance < ICE_STATE_CHANCE ){
            return new IceState();
        } else if (chance < ICE_STATE_CHANCE + FIRE_STATE_CHANCE)
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

    @Override
    public int setStateDuration() {
        return DURATION;
    }

    public void setDragonAction(Animal dragon, GameMap map) {
        Location here = map.locationOf(dragon);
        dragon.resistanceToWarm = false;

        for (Exit exit : here.getExits()) {
            Location surrounding = exit.getDestination();
            surrounding.setGround(new Dirt());
        }



    }
}
