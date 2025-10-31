package game.items.currency;

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
