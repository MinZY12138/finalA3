package game.weapons;

/**
 * <h1>WeaponInfo Enum</h1>
 *
 * <p>
 *      The {@code WeaponInfo} enum defines intrinsic weapon configurations used in the game.
 *      Each weapon type specifies its base damage, attack verb, hit chance percentage, and weapon name.
 * </p>

 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public enum WeaponInfo {
    BARE_FIST(25, "punches", 50,"barefist"),
    TEETH(80, "bite", 75, "teeth");

    private final int damage;

    private final String verb;

    private final int hitChance;

    private final String name;

    /**
     * Constructs a {@code WeaponInfo} constant with the given attributes.
     *
     * @param damage     the amount of damage the weapon
     * @param verb       the verb used in attack descriptions
     * @param hitChance  the weapon's chance to hit the target
     * @param name       the name of the weapon
     */
    WeaponInfo (int damage, String verb, int hitChance, String name) {
        this.damage = damage;
        this.verb = verb;
        this.hitChance = hitChance;
        this.name = name;
    }

    /**
     * Returns the damage value of the weapon.
     *
     * @return the damage
     */
    public int getDamage() { return damage; }

    /**
     * Returns the verb describing the weapon's attack.
     *
     * @return the verb
     */
    public String getVerb() { return verb; }

    /**
     * Returns the weapon's chance to successfully hit a target.
     *
     * @return the hit chance percentage
     */
    public int getHitChance() { return hitChance; }

    /**
     * Returns the nameof the weapon
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
