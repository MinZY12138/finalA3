package game.items.currency;

/**
 * <h1>WalletInjector Interface</h1>
 * <p>
 * The {@code WalletInjector} acts an injector to avoid a concrete class depending on a
 * concrete class.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-10-31
 */
public interface WalletInjector {

    /**
     * Creates and returns a new instance.
     *
     * @return a new Wallet instance
     */
    static Wallet getNewWallet() {
        return new Wallet();
    }
}
