package game.items.equipments.weapons;

/**
 * <h1>Weapon Type enumeration class</h1>
 * <p>
 * The {@code WeaponType} enumeration defines the core configurations of predefined weapon
 * in the game.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-10-03
 */
public enum WeaponType {

    /**
     * Weapon Axe configuration.
     */
    AXE(15, 75, "hacks"),

    /**
     * Weapon Torch configuration.
     */
    TORCH(10, 50, "strikes"),

    /**
     * Weapon Bow configuration.
     */
    BOW(5, 25, "shoots");

    /**
     * The weapon damage.
     */
    private final int DAMAGE;

    /**
     * The weapon hit rate.
     */
    private final int HIT_RATE;

    /**
     * The weapon attack verb.
     */
    private final String VERB;

    /**
     * The constructor of the WeaponType enum.
     *
     * @param damage  the weapon damage
     * @param hitRate the weapon hit rate
     * @param verb    the weapon attack verb
     */
    WeaponType(int damage, int hitRate, String verb) {
        this.DAMAGE = damage;
        this.HIT_RATE = hitRate;
        this.VERB = verb;
    }

    /**
     * The accessor of the weapon damage.
     *
     * @return the weapon damage
     */
    public int getDAMAGE() {
        return DAMAGE;
    }

    /**
     * The accessor of the weapon hit rate.
     *
     * @return the weapon hit rate
     */
    public int getHIT_RATE() {
        return HIT_RATE;
    }

    /**
     * The accessor of the weapon attack verb.
     *
     * @return the weapon attack verb
     */
    public String getVERB() {
        return VERB;
    }
}
