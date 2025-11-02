package game.items.currency;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.ItemInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Wallet class</h1>
 * <p>
 * The {@code Wallet} represents a container for storing ang managing {@link Diamond}
 * currency owned by {@link Actor}.
 * It supports collecting, deducting, displaying balances, and automatically combining lower
 * tier diamonds into higher-tier ones.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public class Wallet extends Item implements WalletFunction {

    /**
     * Stores the number of each diamond tier.
     */
    private final Map<Class<? extends Diamond>, Integer> wallet = new HashMap<>();

    /**
     * Lower bound value.
     */
    private static final int LOWER_BOUND = 0;

    /**
     * The required number of diamonds to combine into a higher tier one.
     */
    private static final int COMBINE_CRITERIA = 2;

    /**
     * Increment 1 value.
     */
    private static final int INCREMENT = 1;

    /***
     * Constructor of the Wallet class.
     */
    public Wallet() {
        super(ItemInfo.WALLET.getNAME(), ItemInfo.WALLET.getCHAR(), ItemInfo.WALLET.isPORTABLE());
    }

    /**
     * Adds a diamond to the wallet and automatically combine diamonds (if any).
     *
     * @param actor   the actor who owns the wallet
     * @param diamond the diamond to be collected
     * @return a message describing any combination that occurred as a result of collection
     */
    public String collect(Actor actor, Diamond diamond) {
        wallet.merge(diamond.getClass(), INCREMENT, Integer::sum);
        return autoCombine(actor);
    }

    /**
     * Deducts a specified diamond from the wallet.
     *
     * @param diamond the diamond tier to be removed
     */
    public void deduct(Diamond diamond) {
        Class<? extends Diamond> diamondType = diamond.getClass();
        int currentBalance = wallet.getOrDefault(diamondType, LOWER_BOUND);
        int updatedBalance = Math.max(LOWER_BOUND, currentBalance - INCREMENT);
        wallet.put(diamondType, updatedBalance);
    }

    /**
     * Get the total number of a specified type of diamonds from the wallet.
     *
     * @param diamond the diamond class to query
     * @return the number of that tier of diamonds
     */
    public int getAmount(Class<? extends Diamond> diamond) {
        return wallet.getOrDefault(diamond, LOWER_BOUND);
    }

    /**
     * Displays the current balances of the wallet: [G: 0, B: 0, R: 0]; G is Green Diamonds; B
     * is Blue Diamond; R is Red Diamond.
     *
     * @return current balances in a human-readable format
     */
    public String showBalance() {
        StringBuilder messages = new StringBuilder("[");
        boolean controller = true;

        for (Map.Entry<Class<? extends Diamond>, Integer> diamond : wallet.entrySet()) {
            if (!controller) {
                messages.append(", ");
            }

            controller = false;

            try {
                Diamond temp = diamond.getKey().getDeclaredConstructor().newInstance();
                messages.append(temp.getDisplayChar()).append(": ").append(diamond.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        messages.append("]");
        return messages.toString();
    }

    /**
     * Automatically combine lower tier diamonds into higher tier diamonds.
     *
     * @param actor the actor who owns the wallet
     * @return a message of combination of diamonds
     */
    private String autoCombine(Actor actor) {
        boolean isCombined;
        String messages = "";

        do {
            isCombined = false;

            for (Class<? extends Diamond> diamondType : new ArrayList<>(wallet.keySet())) {
                int amount = getAmount(diamondType);

                if (amount >= COMBINE_CRITERIA) {
                    try {
                        Diamond diamond = diamondType.getDeclaredConstructor().newInstance();

                        if (diamond.canCombine()) {
                            Diamond nextTier = diamond.getNextTier();
                            int pairs = amount / COMBINE_CRITERIA;
                            int remaining = amount % COMBINE_CRITERIA;
                            wallet.put(diamondType, remaining);
                            wallet.merge(nextTier.getClass(), pairs, Integer::sum);
                            isCombined = true;
                            messages += actor + " combines two " + diamond + " and get a " + nextTier;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } while (isCombined);

        return messages;
    }
}
