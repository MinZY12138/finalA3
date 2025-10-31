package game.items.currency;

/**
 * <h1>SelfRegisterDiamond interface</h1>
 * <p>
 * The {@code SelfRegisterDiamond} interface defines a mechanism that allow each {@link Diamond}
 * subclass to automatically register itself in the game's diamond registry upon class loading.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public interface SelfRegisterDiamond {
    static void ensureRegistered() {
        try {
            Class.forName("game.items.currency.BlueDiamond");
            Class.forName("game.items.currency.GreenDiamond");
            Class.forName("game.items.currency.RedDiamond");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void register(); // each subclass MUST implement this
}
