package game.items.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.statuses.Poisoning;

/**
 * <h1>Class represents YewberryCoating</h1>
 *
 * <p>
 * Represents a {@link Coating} that applies a poisoning effect to the target.
 * When a weapon coated with Yewberry hits an enemy, it inflicts a
 * {@link Poisoning} status lasting 5 turns, dealing 4 HP damage per turn.
 * </p>
 *
 *
 * @author Min Zhengyuan
 * @version 2.0
 */
public class YewBerryCoating extends Coating {

    private static final int DAMAGE = 4;

    private static final int DURATION = 5;

    /**
     * Constructor for YewBerryCoating.
     * Set the name of this coating as "Yew berry".
     */
    public YewBerryCoating(){
        super("Yew berry");
    }

    /**
     * Apply the poisoning effect when the coated weapon hits a target.
     * <p>
     * Attaches a {@link Poisoning} status to the target, lasting 5 turns.
     * </p>
     *
     * @param attacker The actor performing the attack.
     * @param target   The actor being hit.
     * @param map      The map where the attack occurs.
     */
    @Override
    public void applyOnHit(Actor attacker, Actor target, GameMap map) {
        // Attach Poisoning status to target (5 turns, -4 HP each turn)
        target.addStatus(new Poisoning(target, DAMAGE, DURATION));
    }
}
