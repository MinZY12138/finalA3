package game.items;

/**
 * <h1>ItemInfo</h1>
 *
 * <p>
 * Class represent the different item type
 * information (e.g., display character, name)
 * in the system.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public enum ItemInfo {

    TELE_CUBE("Teleport Cube", '□', true),
    APPLE("Apple", 'a', true),
    HAZELNUT("Hazelnut", 'n', true),
    YEW_BERRY("Yew Berry", 'x', true),
    AXE("Axe", 'p', true),
    TORCH("Torch", 'y', true),
    BOW("Bow", 'c', true),
    GREEN_DIAMOND("Green Diamond", 'G', false),
    BLUE_DIAMOND("Blue Diamond", 'B', false),
    RED_DIAMOND("Red Diamond", 'R', false),
    WALLET("Wallet", '$', false),
    DIMENSIONAL_BOTTLE("Dimensional bottle", 'u', true);
    /**
     * The name of the item.
     */
    private final String NAME;

    /**
     * The display char of the item.
     */
    private final char CHAR;

    /**
     * Determine this item can be picked up.
     */
    private final boolean PORTABLE;

    /**
     * Constructor of the ItemInfo class.
     *
     * @param name        the name of the item
     * @param displayChar the display char of the item
     * @param portable    determine this item can be picked up
     */
    ItemInfo(String name, char displayChar, boolean portable) {
        this.NAME = name;
        this.CHAR = displayChar;
        this.PORTABLE = portable;
    }

    /**
     * Getter of the item name.
     *
     * @return the name of the item
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Getter of the item display char.
     *
     * @return the display char of the item
     */
    public char getCHAR() {
        return CHAR;
    }

    /**
     * Determine this item can be picked up.
     *
     * @return true if item can be picked up, false otherwise.
     */
    public boolean isPORTABLE() {
        return PORTABLE;
    }
}
