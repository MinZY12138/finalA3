package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

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
    DragonState getNextState();

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
