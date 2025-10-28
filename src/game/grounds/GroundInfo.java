package game.grounds;

/**
 * <h1>GroundInfo</h1>
 *
 * <p>
 *     Class represent the different ground type
 *     information (e.g., display character, name)
 *     in the system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public enum GroundInfo
{
    CAVE ('C', "Cave"),
    MEADOW('w', "Meadow"),
    TUNDRA('_', "Tundra"),
    TELE_DOOR('#', "Tele-door"),
    TELEPORTATION_CIRCLE('O', "Teleportation Circle"),
    DIRT ('+', "Dirt"),
    FIRE ('^', "Fire"),
    SNOW ('.', "Snow"),
    APPLE_SPROUTS (',', "Apple Sprouts"),
    APPLE_SAPLING ('t', "Apple Sapling"),
    YEW_BERRY_SAPLING ('b', "Yew Berry Sapling");


    private final char DISPLAY_CHAR;

    private final String NAME;

    /**
     * Constructor for GroundInfo
     * @param DISPLAY_CHAR the character representation of the ground
     * @param NAME the name of the ground
     */
    GroundInfo (char DISPLAY_CHAR, String NAME)
    {
        this.DISPLAY_CHAR = DISPLAY_CHAR;
        this.NAME = NAME;
    }

    /**
     * Getter to get the display character for this ground.
     * @return {@code char} the character representation of this ground.
     */
    public char getDISPLAY_CHAR()
    {
        return DISPLAY_CHAR;
    }

    /**
     * Getter to get the name for this ground.
     * @return {@code String} the name of this ground.
     */
    public String getNAME()
    {
        return NAME;
    }
}
