package game.actors;

import game.items.currency.Wallet;

public interface WalletInjector {
    static Wallet getNewWallet(){
        return new Wallet();
    }
}
