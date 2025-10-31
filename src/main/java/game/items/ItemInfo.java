package game.items;

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

    RED_DIAMOND("Red Diamond", 'R', false);

    private final String NAME;

    private final char CHAR;

    private final boolean PORTABLE;

    ItemInfo(String name, char displayChar, boolean portable) {
        this.NAME = name;
        this.CHAR = displayChar;
        this.PORTABLE = portable;
    }

    public String getNAME() {
        return NAME;
    }

    public char getCHAR() {
        return CHAR;
    }

    public boolean isPORTABLE() {
        return PORTABLE;
    }
}
