package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.statuses.Bleeding;

/**
 * <h1>Axe class</h1>
 * <p>
 * The {@code Axe} is a {@link LootWeapon}.
 * When actors use it to attack others, it has a chance to cause target actors to {@link Bleeding}.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.1
 * @since 2025-09-24
 */
public class Axe extends LootWeapon {

    /**
     * Weapon damage.
     */
    private static final int HIT_DMG = 15;

    /**
     * Rate of hitting target actors.
     */
    private static final int HIT_RATE = 75;

    /**
     * Bleeding damage
     */
    private static final int BLEEDING_DMG = 10;

    /**
     * Chance to make the target bleed.
     */
    private static final int BLEEDING_RATE = 50;

    /**
     * Total turns of the continuous damage.
     */
    private static final int DURATION = 2;

    /**
     * The constructor of the Axe class.
     */
    public Axe() {
        super("axe", 'p', true, HIT_DMG, HIT_RATE, "hacks");
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
        if (RAND.nextInt(100) <= BLEEDING_RATE) {
            target.addStatus(new Bleeding(target, BLEEDING_DMG, DURATION));
        }
    }
}