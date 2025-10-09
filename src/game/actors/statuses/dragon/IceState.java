package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Abilities;
import game.actors.animals.Animal;

/**
 * <h1>Class IceState</h1>
 *
 * <p>
 *     Represent the ice state of the dragon in this system.
 *     Will make the surrounding to became {Snow} when the
 *     dragon moved. Also, will have cold resistance.
 * </p>
 *
 * Extends: {@link StateOfDragon}
 *
 * @author Ng Jun Jie
 * @version 2.0
 * Modify by: Shee Seng Cheng
 */
public class IceState extends StateOfDragon{

    /**
     * Set the dragon effect when is in this State.
     * @param dragon the dragon
     * @param map the map where the dragon was
     */
    public void setDragonAction(Animal dragon, GameMap map) {
        super.setDragonAction(dragon, map);
        dragon.enableAbility(Abilities.COLD_RESISTANT);
    }
}
