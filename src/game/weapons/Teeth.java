package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Teeth class representing an intrinsic weapon
 * This intrinsic weapon deals 80 damage points with a 75% chance
 * to hit the target.
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Teeth extends IntrinsicWeapon {
    public Teeth() {
        super(80, "bite", 75, "teeth");
    }
}
