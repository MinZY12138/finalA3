package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

import java.util.Random;

/**
 * <h1>
 *     Abstract class StateOfDragon
 * </h1>
 *
 * <p>
 *     Represent the parent's state of the dragon.
 *     It has all the default method to manage the transformation to
 *     different state of the dragon.
 * </p>
 */
public abstract class StateOfDragon implements DragonState
{
    /**
     * Use to get random number.
     */
    private static final Random RAND = new Random();

    /**
     * Method to decide which state next.
     * @return {@link DragonState} the chosen state of the dragon.
     */
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

    /**
     * A simple name representation of the current state.
     * @return {@code String} the name of the state.
     */
    @Override
    public String getStateName(){
        return this.getClass().getSimpleName();
    }

    /**
     * Getter to get the state duration.
     * @return {@code int} duration of the state.
     */
    @Override
    public int getStateDuration() {
        return StateDragonInfo.DURATION.getINFO();
    }

    /**
     * Set the dragon effect when is in this State.
     *
     * @param dragon the dragon
     * @param map    the map where the dragon was
     */
    @Override
    public void setDragonAction(Animal dragon, GameMap map)
    {
        dragon.resistanceToWarm = false;
        dragon.resetBehaviourMap();
    }
}
