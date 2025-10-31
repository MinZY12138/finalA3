package game.items.currency;

import edu.monash.fit2099.engine.actors.Actor;

public interface WalletFunction {
    String collect(Actor actor, Diamond diamond);

    void deduct(Diamond diamond);

    int getAmount(Class<? extends Diamond> diamond);

    String showBalance();
}
