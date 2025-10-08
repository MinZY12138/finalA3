package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.capabilities.SummonDirt;

/**
 * <h1>Class DirtState</h1>
 *
 * <p>
 *     Represent the dirt state of the dragon in this system.
 *     Will make the surrounding to became Dirt when the
 *     dragon moved.
 *     More info see {@link SummonDirt}
 * </p>
 *
 * Extends: {@link StateOfDragon}
 * Implements: {@link SummonDirt}
 *
 * @author Ng Jun Jie
 * @version 2.0
 * Modify by: Shee Seng Cheng
 */
public class DirtState extends StateOfDragon implements SummonDirt{

    /**
     * Set the dragon effect when is in this State.
     * @param dragon the dragon
     * @param map the map where the dragon was
     */
    public void setDragonAction(Animal dragon, GameMap map) {
        super.setDragonAction(dragon, map);
        Location here = map.locationOf(dragon);

        for (Exit exit : here.getExits()) {
            Location surrounding = exit.getDestination();
            surrounding.setGround(this.summonDirt());
        }
    }
}
