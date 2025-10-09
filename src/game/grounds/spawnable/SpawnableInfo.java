package game.grounds.spawnable;

public enum SpawnableInfo
{
    TUNDRA_SPAWN_TURN (1),
    TUNDRA_SPAWN_CHANCE (5),
    MEADOW_SPAWN_TURN (7),
    MEADOW_SPAWN_CHANCE (50),
    CAVE_SPAWN_TURN (5),
    CAVE_SPAWN_CHANCE (100);

    private final int INFO;

    SpawnableInfo(int info)
    {
        this.INFO = info;
    }

    public int getINFO()
    {
        return INFO;
    }
}
