package game.grounds.dimensional;

/**
 * Represents the different styles of Mysterio stores that can manifest from a dimensional rift.
 */
public enum DimensionalStoreType {
    ARMOURY("Armoury"),
    ALCHEMY("Alchemy"),
    CURIOSITY("Curiosity");

    private final String description;

    DimensionalStoreType(String description) {
        this.description = description;
    }

    /**
     * Readable description for menu and logging.
     *
     * @return textual description
     */
    public String getDescription() {
        return description;
    }
}