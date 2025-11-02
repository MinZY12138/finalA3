package game.items.equipments.armors;

/**
 * <h1>ArmorInfo Enumeration</h1>
 * <p>
 * The {@code ArmorInfo} enum represents the defense value of each {@link Armor}.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public enum ArmorInfo
{
    DIAMOND_ARMOR(10),
    IRON_ARMOR(5),
    LEATHER_ARMOR(2);

    /**
     * The defense value of the armor.
     */
    private final int DEFENSE;

    /**
     * Constructor of the ArmorInfo class.
     *
     * @param defense the defense value of the armor
     */
    ArmorInfo(int defense)
    {
        this.DEFENSE = defense;
    }

    /**
     * Getter of the armor defense value
     *
     * @return the defense value of the armor
     */
    public int getDEFENSE()
    {
        return DEFENSE;
    }
}
