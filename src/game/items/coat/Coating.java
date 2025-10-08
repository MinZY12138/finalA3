package game.items.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * <h1>Interface represents Coating</h1>
 *
 * <p>
 * Represents an effect that can be applied to a {@link game.items.coat.Coatable} weapon.
 * When the coated weapon successfully hits a target, the {@link #applyOnHit(Actor, Actor, GameMap)}
 * method defines what happens (e.g., apply poison or frostbite effects).
 * </p>
 *
 * <p>
 * This interface is implemented by concrete coating types such as
 * {@code YewberryCoating} and {@code SnowCoating} in REQ4: Coating.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0
 */
public abstract class Coating {

    private final String NAME;

    public Coating(String name) {
        this.NAME = name;
    }

    /**
     * Get the name of this coating.
     *
     * @return A string representing the coating's name.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Defines the effect that occurs when a coated weapon hits a target.
     *
     * @param attacker The actor performing the attack.
     * @param target   The target actor being hit.
     * @param map      The game map where the attack occurs.
     */
    public abstract void applyOnHit(Actor attacker, Actor target, GameMap map);
}
