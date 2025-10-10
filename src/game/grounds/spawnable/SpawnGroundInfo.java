package game.grounds.spawnable;

/**
 * <h1>SpawnableGroundInfo</h1>
 *
 * <p>
 *     Class represent the different spawnable (animal)
 *     ground information
 *     in the system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public enum SpawnGroundInfo
{
    TUNDRA (1,5),
    MEADOW (7,50),
    CAVE(5,100);

    private final int SPAWN_TURN;
    private final int SPAWN_CHANCE;

    /**
     * Constructor for SpawnableGroundInfo
     * @param spawnTurn the turn that this ground should spawn an animal
     * @param spawnChance the probability to spawn an animal at that moment.
     */
    SpawnGroundInfo(int spawnTurn, int spawnChance)
    {
        this.SPAWN_TURN = spawnTurn;
        this.SPAWN_CHANCE = spawnChance;
    }

    /**
     * Getter get the spawn turn
     * @return {@code int} the spawn turn
     */
    public int getSPAWN_TURN()
    {
        return this.SPAWN_TURN;
    }

    /**
     * Getter get the spawn chance
     * @return {@code int} probability to spawn
     */
    public int getSPAWN_CHANCE()
    {
        return SPAWN_CHANCE;
    }
}
