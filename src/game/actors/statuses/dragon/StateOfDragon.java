package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;


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
