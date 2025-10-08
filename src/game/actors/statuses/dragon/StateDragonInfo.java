package game.actors.statuses.dragon;

public enum StateDragonInfo
{
    FIRE_STATE_CHANCE (30),
    ICE_STATE_CHANCE (55),
    DIRT_STATE_CHANCE (85),
    RANDOM_BOUND (100),
    DURATION (2);

    private final int INFO;
    StateDragonInfo (int info)
    {
        this.INFO = info;
    }

    public int getINFO()
    {
        return INFO;
    }
}
