package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.SummonFire;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 * @author Adrian Kristanto
 */
public class FireBlow extends IntrinsicWeapon implements SummonFire {
    public FireBlow() {
        super(30, "splash fire", 100, "fire blow");
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {

        Location burnPlace = map.locationOf(target);

        burnLocation(burnPlace);

        return super.attack(attacker, target, map);
    }
}

