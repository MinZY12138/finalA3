package game.items.equipments.armors;

public enum ArmorInfo
{
    DIAMOND_DEFENSE (10),
    IRON_DEFENSE(5),
    LEATHER_ARMOR(2);

    private final int DEFENSE;

    ArmorInfo(int defense) {
        this.DEFENSE = defense;
    }

    public int getDEFENSE()
    {
        return DEFENSE;
    }
}
