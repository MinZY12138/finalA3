package game.items.currency;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>WalletFunction interface</h1>
 * <p>
 *     The {@code WalletFunction} represents the common method for {@link Wallet} class.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public interface WalletFunction {

    /**
     * Adds a diamond to the wallet and automatically combine diamonds (if any).
     *
     * @param actor   the actor who owns the wallet
     * @param diamond the diamond to be collected
     * @return a message describing any combination that occurred as a result of collection
     */
    String collect(Actor actor, Diamond diamond);

    /**
     * Deducts a specified diamond from the wallet.
     *
     * @param diamond the diamond tier to be removed
     */
    void deduct(Diamond diamond);

    /**
     * Get the total number of a specified type of diamonds from the wallet.
     *
     * @param diamond the diamond class to query
     * @return the number of that tier of diamonds
     */
    int getAmount(Class<? extends Diamond> diamond);

    /**
     * Displays the current balances of the wallet: [G: 0, B: 0, R: 0]; G is Green Diamonds; B
     * is Blue Diamond; R is Red Diamond.
     *
     * @return current balances in a human-readable format
     */
    String showBalance();
}
