package game.actors.statuses.dragon;

/**
 * <h1>Enum StateDragonInfo</h1>
 *
 * <p>
 *     Represent the constant value for the dragon,
 *     such as the possibilities of transforming state,
 *     or duration to transform etc...
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public enum StateDragonInfo
{
    FIRE_STATE_CHANCE (30),
    ICE_STATE_CHANCE (55),
    DIRT_STATE_CHANCE (85),
    RANDOM_BOUND (100),
    DURATION (2);

    /**
     * Attribute to store the info.
     */
    private final int INFO;

    /**
     * Constructor for enum StateDragonInfo
     * @param info the integer value of the information (e.g., chances etc...)
     */
    StateDragonInfo (int info)
    {
        this.INFO = info;
    }

    /**
     * Getter to get the info.
     * @return {@code int} value of the info.
     */
    public int getINFO()
    {
        return INFO;
    }
}
