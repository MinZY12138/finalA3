package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

/**
 * <h1>Class FireState</h1>
 *
 * <p>
 *     Represent the fire state of the dragon in this system.
 *     Will has a behaviour to splash fire to other actor if it has.
 *     More details see {@link SplashBehaviourInjector}
 * </p>
 *
 * Extends: {@link StateOfDragon}
 * Implements: {@link SplashBehaviourInjector}
 *
 * @author Ng Jun Jie
 * @version 2.0
 * Modify by: Shee Seng Cheng
 */
public class FireState extends StateOfDragon implements SplashBehaviourInjector{
    /**
     * Set the dragon effect when is in this State.
     * @param dragon the dragon
     * @param map the map where the dragon was
     */
    public void setDragonAction(Animal dragon, GameMap map) {
        super.setDragonAction(dragon, map);
        int HIGHEST_PRIORITY = 0;
        //Game rule attack has the most priority in the system.
        dragon.addBehaviourToAnimal(this.newBehaviourSplash(), HIGHEST_PRIORITY);
    }
}
