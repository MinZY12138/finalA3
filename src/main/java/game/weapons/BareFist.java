package game.weapons;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 * @author Adrian Kristanto
 */
public class BareFist extends ModifyIntrinsicWeapon {
    public BareFist() {
        super(WeaponInfo.BARE_FIST.getDamage(),
                WeaponInfo.BARE_FIST.getVerb(),
                WeaponInfo.BARE_FIST.getHitChance(),
                WeaponInfo.BARE_FIST.getName());
    }
}