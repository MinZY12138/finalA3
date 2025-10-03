package game.items.equipments.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Warmable;
import game.actors.statuses.Frosting;
import game.actors.Abilities; // Ensure Abilities.COLD_RESISTANT exists in your project

/**
 * Coating that applies frostbite on hit.
 * - If the target has COLD_RESISTANT ability, the effect is ignored.
 * - Otherwise, if the target exposes Warmable capability, apply Frosting for 3 turns.
 */
public class SnowCoating implements Coating {

    @Override
    public String name() {
        return "Snow";
    }

    @Override
    public void applyOnHit(Actor attacker, Actor target, GameMap map) {
        // Skip if target is cold-resistant (e.g., spawned from Tundra)
        if (target.hasAbility(Abilities.COLD_RESISTANT)) {
            return;
        }

        // Adapt to Warmable capability without using instanceof
        target.asCapability(Warmable.class).ifPresent(warmable -> {
            target.addStatus(new Frosting(warmable, 3));
        });
    }
}
