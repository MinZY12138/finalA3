package game.weapons;

/**
 * Teeth class representing an intrinsic weapon
 * This intrinsic weapon deals 80 damage points with a 75% chance
 * to hit the target.
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Teeth extends ModifyIntrinsicWeapon {
    public Teeth() {
        super(WeaponInfo.TEETH.getDamage(),
                WeaponInfo.TEETH.getVerb(),
                WeaponInfo.TEETH.getHitChance(),
                WeaponInfo.TEETH.getName());
    }

}
