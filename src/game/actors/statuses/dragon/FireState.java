package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;
import game.behaviours.SplashBehaviour;

/**
 * <h1>Class FireState</h1>
 *
 * <p>
 *     Represent the fire state of the dragon in this system.
 *     Will has a behaviour to splash fire to other actor if has.
 *     More details see {@link SplashBehaviour}
 * </p>
 *
 * Extends: {@link StateOfDragon}
 * @author Ng Jun Jie
 * @version 2.0
 * Modify by: Shee Seng Cheng
 */
public class FireState extends StateOfDragon{
    /**
     * Set the dragon effect when is in this State.
     * @param dragon the dragon
     * @param map the map where the dragon was
     */
    public void setDragonAction(Animal dragon, GameMap map) {
        super.setDragonAction(dragon, map);
        dragon.resistanceToWarm = false;
        int HIGHEST_PRIORITY = 0;
        //Game rule attack has the most priority in the system.
        dragon.addBehaviourToAnimal(new SplashBehaviour(), HIGHEST_PRIORITY);
    }
}
