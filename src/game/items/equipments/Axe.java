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
 * @version 2.0.1
 * @since 2025-09-24
 */
public class Axe extends LootWeapon {

    /**
     * Defining status attributes.
     */
    private final StatusType STATUS;

    /**
     * Chance to make the target bleed.
     */
    private static final int CHANCE = 50;

    /**
     * The constructor of the Axe class.
     *
     * @param type   defining damage, hit rate, and verb
     * @param status defining bleeding damage and duration
     */
    public Axe(WeaponType type, StatusType status) {
        super("Axe", 'p', true, type);
        this.STATUS = status;
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
        if (RAND.nextInt(100) <= CHANCE) {
            target.addStatus(new Bleeding(target, STATUS.getDAMAGE(), STATUS.getDURATION()));
        }
    }
}