package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.SummonFire;

/**
 * Class representing an intrinsic weapon called a FireBlow.
 * This intrinsic weapon deals 30 damage points with a 100% chance
 * to hit the target. And burn the location of the target.
 *
 * Extends: {@link IntrinsicWeapon}
 * Implement: {@link SummonFire}
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class FireBlow extends IntrinsicWeapon implements SummonFire {
    /**
     * Constructor for FireBlow.
     */
    public FireBlow() {
        super(30, "splash fire", 100, "fire blow");
    }

    /**
     * override attack same implementation using super but additional functionality
     * burn a single location.
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return the description once the attack is done
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {

        Location burnPlace = map.locationOf(target);

        burnLocation(burnPlace);

        return super.attack(attacker, target, map);
    }
}

