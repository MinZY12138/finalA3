package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Seller;
import game.items.currency.WalletFunction;
import game.mysteriostore.Merchandise;
import game.mysteriostore.Price;

import java.util.List;

/**
 * <h1>Buy Action</h1>
 * <p>
 * Enables an actor to purchase merchandise from a seller. The action ensures
 * that the buyer pays the required diamonds and receives the newly created
 * item.
 * </p>
 */
public class BuyAction extends Action {

    private final Seller seller;
    private final Merchandise merchandise;

    /**
     * Constructs a buy action targeting a specific seller and merchandise.
     *
     * @param seller       the seller offering the merchandise
     * @param merchandise  the merchandise to be purchased
     */
    public BuyAction(Seller seller, Merchandise merchandise) {
        this.seller = seller;
        this.merchandise = merchandise;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<WalletFunction> wallets = actor.getItemInventoryAs(WalletFunction.class);
        if (wallets.isEmpty()) {
            return actor + " needs a wallet to buy " + merchandise.getName() + ".";
        }

        WalletFunction wallet = wallets.get(0);
        Price price = merchandise.getPrice();

        if (!price.isAffordable(wallet)) {
            return actor + " cannot afford " + merchandise.getName() + ". Cost: " + price.describe();
        }

        Item item = merchandise.createItem();
        String paymentDescription = price.charge(actor, wallet);
        actor.addItemToInventory(item);

        return actor + " buys " + item + " from " + seller + " for " + price.describe() + ".\n"
                + paymentDescription;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + merchandise.getName() + " for " + merchandise.getPrice().describe();
    }
}
