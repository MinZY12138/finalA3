package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.statuses.ContinuousDamage;

/**
 * <h1>Axe class</h1>
 * <p>
 * The {@code Axe} is a {@link LootWeapon}.
 * When actors use it to attack others, it has a chance to cause target actors to bleeding.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 2.0.1
 * @since 2025-09-24
 */
public class Axe extends LootWeapon {

    /**
     * Defining status attributes.
     */
    private final StatusType EFFECT;

    /**
     * Chance to make the target bleed.
     */
    private static final int CHANCE = 50;

    /**
     * The constructor of the Axe class.
     *
     * @param type   defining damage, hit rate, and verb
     * @param effect defining bleeding damage and duration
     */
    public Axe(WeaponType type, StatusType effect) {
        super("Axe", 'p', true, type);
        this.EFFECT = effect;
    }

    /**
     * Attack a target actor with the bleeding effect.
     *
     * @param attacker represent an actor attack
     * @param target   represent an actor being attacked
     * @param map      the game map
     */
    @Override
    public void hit(Actor attacker, Actor target, GameMap map) {
        int maximumBound = 100;

        if (RAND.nextInt(maximumBound) <= CHANCE) {
            ContinuousDamage status = EFFECT.createStatus(target);

            if (status != null) {
                target.addStatus(status);
            }
        }
    }
}