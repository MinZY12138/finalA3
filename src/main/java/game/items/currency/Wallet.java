package game.items.currency;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private final Map<Class<? extends Diamond>, Integer> wallet = new HashMap<>();

    private static final int LOWER_BOUND = 0;

    private static final int COMBINE_CRITERIA = 2;

    public String collect(Actor actor, Diamond diamond) {
        wallet.merge(diamond.getClass(), 1, Integer::sum);
        return autoCombine(actor);
    }

    public void deduct(Diamond diamond) {
        wallet.merge(diamond.getClass(), -1, (oldAmount, newAmount) -> {
            int updatedAmount = oldAmount + newAmount;
            return Math.max(LOWER_BOUND, updatedAmount);
        });
    }

    public int getAmount(Class<? extends Diamond> diamond) {
        return wallet.getOrDefault(diamond, LOWER_BOUND);
    }

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
