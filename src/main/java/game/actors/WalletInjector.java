package game.actors;

import game.items.currency.Wallet;

public interface WalletInjector {
    default Wallet getNewWallet(){
        return new Wallet();
    }
}
