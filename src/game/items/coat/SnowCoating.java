package game.items.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Warmable;
import game.actors.statuses.Frosting;
import game.actors.abilities.Abilities;

/**
 * <h1>Class represents SnowCoating</h1>
 *
 * <p>
 * Represents a {@link Coating} that applies a frostbite effect on hit.
 * When the coated weapon hits a target:
 * <ul>
 *     <li>If the target has the {@code COLD_RESISTANT} ability, the effect is ignored.</li>
 *     <li>Otherwise, if the target exposes {@link Warmable} capability, a {@link Frosting}
 *     status is applied for 3 turns.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implements {@link Coating}.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0
 */
public class SnowCoating implements Coating {

    /**
     * Get the name of this coating.
     *
     * @return The string {@code "Snow"}.
     */
    @Override
    public String name() {
        return "Snow";
    }

    /**
     * Apply the frostbite effect when the coated weapon hits a target.
     * <p>
     * - Ignores targets with {@code COLD_RESISTANT} ability.<br>
     * - Applies {@link Frosting} to any target exposing {@link Warmable}.
     * </p>
     *
     * @param attacker The actor performing the attack.
     * @param target   The actor being hit.
     * @param map      The map where the attack occurs.
     */
    @Override
    public void applyOnHit(Actor attacker, Actor target, GameMap map) {
        // Skip if target is cold-resistant (e.g., spawned from Tundra)
        if (target.hasAbility(Abilities.COLD_RESISTANT)) {
            return;
        }

        // Apply frostbite if target exposes Warmable capability (no instanceof used)
        target.asCapability(Warmable.class).ifPresent(warmable -> {
            target.addStatus(new Frosting(warmable, 3));
        });
    }
}
