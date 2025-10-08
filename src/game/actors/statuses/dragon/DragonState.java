package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

import java.util.Random;

/**
 * <h1>Interface DragonState</h1>
 * <p>
 *     Represent the state of the dragon,
 *     only implemented by StateOfDragon in this system.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 * Modify by: Shee Seng Cheng
 */
public interface DragonState {
    /**
     * A simple name representation of the current state.
     * @return {@code String} the name of the state.
     */
    String getStateName();

    /**
     * Method to decide which state next.
     * @return {@link DragonState} the chosen state of the dragon.
     */
    default DragonState getNextState(Random rand){
        int chance  = rand.nextInt(StateDragonInfo.RANDOM_BOUND.getINFO());

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
            return null;
        }
    }

    /**
     * Set the dragon effect when is in this State.
     * @param dragon the dragon
     * @param map the map where the dragon was
     */
    void setDragonAction(Animal dragon, GameMap map);

    /**
     * Getter to get the state duration.
     * @return {@code int} duration of the state.
     */
    int getStateDuration();
}
