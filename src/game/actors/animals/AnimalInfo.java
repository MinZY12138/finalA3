package game.actors.animals;

/**
 * <h1>AnimalInfo</h1>
 *
 * <p>
 *     Class represent the different animal information
 *     in the system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public enum AnimalInfo
{
    BEAR ("Bear", 'b', 100, 50),
    DEER ("Deer", 'd', 50, 10),
    DRAGON("Dragon", 'Q', 500, 10),
    WOLF("Wolf", 'e', 100, 25);

    private final String NAME;
    private final char DISPLAY_CHARACTER;
    private final int HIT_POINT;
    private final int WARMTH_LEVEL;

    /**
     * Constructor for AnimalInfo
     * @param name of the animal (String)
     * @param displayChar the character represent the animal (char)
     * @param hitPoint the health of the animal (int)
     * @param warmthLevel the initial warmth level of the animal (int)
     */
    AnimalInfo(String name, char displayChar, int hitPoint, int warmthLevel)
    {
        this.NAME = name;
        this.DISPLAY_CHARACTER = displayChar;
        this.HIT_POINT = hitPoint;
        this.WARMTH_LEVEL = warmthLevel;
    }

    /**
     * Getter to get name of the animal.
     * @return {@code String} of the animal name.
     */
    public String getNAME()
    {
        return NAME;
    }

    /**
     * Getter to get display character of the animal.
     * @return {@code char} of the animal character.
     */
    public char getDISPLAY_CHARACTER()
    {
        return DISPLAY_CHARACTER;
    }

    /**
     * Getter to get initial health of the animal.
     * @return {@code int} of the animal initial health.
     */
    public int getHIT_POINT()
    {
        return HIT_POINT;
    }

    /**
     * Getter to get initial warmth level of the animal.
     * @return {@code int} of the animal initial warmth level.
     */
    public int getWARMTH_LEVEL()
    {
        return WARMTH_LEVEL;
    }
}
