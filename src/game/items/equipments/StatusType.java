package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.statuses.Bleeding;
import game.actors.statuses.Burning;
import game.actors.statuses.ContinuousDamage;

/**
 * <h1>Status Type enumeration</h1>
 * <p>
 * The {@code StatusType} enumeration defines predefines status effects in the game.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 2.0.0
 * @since 2025-10-03
 */
public enum StatusType {

    /**
     * Burning status effect.
     */
    BURNING(Burning.class, 3, 7),

    /**
     * Bleeding status effect.
     */
    BLEEDING(Bleeding.class, 10, 2);

    /**
     * The damage caused by the status effect.
     */
    private final int DAMAGE;

    /**
     * The duration of the status effect.
     */
    private final int DURATION;

    /**
     * The status applies to the target actor.
     */
    private final Class<? extends ContinuousDamage> STATUS;

    /**
     * The constructor of the StatusType enum.
     *
     * @param damage   the damage caused by the status effect
     * @param duration the duration of the status effect
     */
    StatusType(Class<? extends ContinuousDamage> status, int damage, int duration) {
        this.STATUS = status;
        this.DAMAGE = damage;
        this.DURATION = duration;
    }

    /**
     * The accessor of the status effect damage.
     *
     * @return the status effect damage
     */
    public int getDAMAGE() {
        return DAMAGE;
    }

    /**
     * The accessor of the status effect duration.
     *
     * @return the status effect duration
     */
    public int getDURATION() {
        return DURATION;
    }

    /**
     * Create a new status effect based on different weapon.
     *
     * @param target the actor that the status effect will be applied to
     * @return a new status object representing the status effect for the given target or null
     * if the instantiation fails
     */
    public ContinuousDamage createStatus(Actor target) {
        try {
            return STATUS.getConstructor(Actor.class, int.class, int.class)
                    .newInstance(target, this.getDAMAGE(), this.getDURATION());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
