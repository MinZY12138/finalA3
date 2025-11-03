package game.mysteriostore;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.currency.Diamond;
import game.items.currency.WalletFunction;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * <h1>Price</h1>
 * <p>
 * Represents the cost of an item in terms of diamonds of different tiers. It
 * encapsulates the logic for checking affordability and deducting the required
 * diamonds from a wallet.
 * </p>
 */
public final class Price {

    private final Map<Class<? extends Diamond>, Integer> costs;

    private Price(Map<Class<? extends Diamond>, Integer> costs) {
        this.costs = Map.copyOf(costs);
    }

    /**
     * Creates a new builder for constructing {@link Price} objects.
     *
     * @return a builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Checks whether the provided wallet can afford this price.
     *
     * @param wallet the wallet capability of the buyer
     * @return {@code true} if the wallet has sufficient diamonds, {@code false} otherwise
     */
    public boolean isAffordable(WalletFunction wallet) {
        for (Map.Entry<Class<? extends Diamond>, Integer> entry : costs.entrySet()) {
            if (wallet.getAmount(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Deducts the required diamonds from the wallet.
     *
     * @param buyer  the actor paying for the purchase
     * @param wallet the wallet capability used for the deduction
     * @return a descriptive message of the deduction
     */
    public String charge(Actor buyer, WalletFunction wallet) {
        if (costs.isEmpty()) {
            return buyer + " pays nothing.";
        }

        StringJoiner joiner = new StringJoiner(", ");

        for (Map.Entry<Class<? extends Diamond>, Integer> entry : costs.entrySet()) {
            Diamond diamond = instantiate(entry.getKey());
            int quantity = entry.getValue();

            for (int count = 0; count < quantity; count++) {
                wallet.deduct(diamond);
            }

            joiner.add(quantity + " " + diamond);
        }

        return buyer + " pays " + joiner + ".";
    }

    /**
     * Provides a human-readable description of the price.
     *
     * @return the description string
     */
    public String describe() {
        if (costs.isEmpty()) {
            return "free";
        }

        StringJoiner joiner = new StringJoiner(" + ");
        for (Map.Entry<Class<? extends Diamond>, Integer> entry : costs.entrySet()) {
            Diamond diamond = instantiate(entry.getKey());
            joiner.add(entry.getValue() + " " + diamond);
        }
        return joiner.toString();
    }

    private Diamond instantiate(Class<? extends Diamond> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Unable to create diamond instance for price calculation.", e);
        }
    }

    /**
     * Builder used to configure {@link Price} objects without exposing the
     * internal map structure.
     */
    public static final class Builder {
        private final Map<Class<? extends Diamond>, Integer> costs = new LinkedHashMap<>();

        /**
         * Adds a diamond requirement to the price.
         *
         * @param type     the diamond class
         * @param quantity how many diamonds of that class are required
         * @return the builder instance for method chaining
         */
        public Builder add(Class<? extends Diamond> type, int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive.");
            }

            costs.merge(type, quantity, Integer::sum);
            return this;
        }

        /**
         * Builds a {@link Price} instance.
         *
         * @return a price containing all configured diamond requirements
         */
        public Price build() {
            return new Price(costs);
        }
    }
}