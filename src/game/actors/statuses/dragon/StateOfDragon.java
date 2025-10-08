package game.actors.statuses.dragon;

import java.util.Random;

public abstract class StateOfDragon implements DragonState
{
    private static final Random RAND = new Random();

    @Override
    public DragonState getNextState(){
        int chance  = RAND.nextInt(StateDragonInfo.RANDOM_BOUND.getINFO());

        //Range between 0-29 inclusive Fire state.
        if (chance < StateDragonInfo.FIRE_STATE_CHANCE.getINFO())
        {
            return new FireState();
        }
        //Range between 30-54 inclusive Ice state.
        else if (chance < StateDragonInfo.ICE_STATE_CHANCE.getINFO())
        {
            return new IceState();
        }
        //Range between 55-84 inclusive Dirt State.
        else if (chance < StateDragonInfo.DIRT_STATE_CHANCE.getINFO())
        {
            return new DirtState();
        }
        //Stay chance 85 and above bound by 100 (85-99) inclusive.
        else {
            return this;
        }
    }

    @Override
    public String getStateName(){
        return this.getClass().getSimpleName();
    }

    @Override
    public int setStateDuration() {
        return StateDragonInfo.DURATION.getINFO();
    }
}
