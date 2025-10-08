package game.items.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * <h1>Class represents Coating</h1>
 *
 * <p>
 * Represent an effect that can be applied to a {@link Coatable} weapon.
 * When the coated weapon hits a target, this class defines what effect happen,
 * such as poison or frostbite.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 2.0
 *
 *
 */
public abstract class Coating {

    /**
     * The name of this coating.
     */
    private final String NAME;

    /**
     * Constructor for Coating.
     *
     * @param name the name of this coating
     */
    public Coating(String name) {
        this.NAME = name;
    }

    /**
     * Get the name of this coating.
     *
     * @return the name of the coating
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Apply the coating effect when a coated weapon hits a target.
     *
     * @param attacker The actor performing the attack.
     * @param target   The target actor being hit.
     * @param map      The game map where the attack occurs.
     */
    public abstract void applyOnHit(Actor attacker, Actor target, GameMap map);
}
