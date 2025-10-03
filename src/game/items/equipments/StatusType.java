package game.items.equipments;

/**
 * <h1>Status Type enumeration</h1>
 * <p>
 * The {@code StatusType} enumeration defines predefines status effects in the game.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-10-03
 */
public enum StatusType {

    /**
     * Burning status effect.
     */
    BURNING(3, 7),

    /**
     * Bleeding status effect.
     */
    BLEEDING(10, 2);

    /**
     * The damage caused by the status effect.
     */
    private final int DAMAGE;

    /**
     * The duration of the status effect.
     */
    private final int DURATION;

    /**
     * The constructor of the StatusType enum.
     *
     * @param damage the damage caused by the status effect
     * @param duration the duration of the status effect
     */
    StatusType(int damage, int duration) {
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
}
