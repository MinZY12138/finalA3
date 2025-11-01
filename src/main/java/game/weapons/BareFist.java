package game.weapons;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 * @author Adrian Kristanto
 */
public class BareFist extends ModifyIntrinsicWeapon {
    public BareFist() {
        super(25, "punches", 50, "bare fist");
    }
}