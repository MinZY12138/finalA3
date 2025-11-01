package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.items.currency.WalletFunction;
import game.mysteriostore.Merchandise;

import java.util.List;

/**
 * <h1>Seller</h1>
 * <p>
 * Represents a stationary NPC that offers merchandise to visiting actors. Each
 * item can be sold at a different price and the seller ensures that the buyer
 * is charged in diamonds.
 * </p>
 */
public class Seller extends Actor {

    private final List<Merchandise> catalogue;

    /**
     * Constructs a seller with the provided catalogue.
     *
     * @param name      seller name
     * @param display   display character
     * @param catalogue list of merchandise offered
     */
    public Seller(String name, char display, List<Merchandise> catalogue) {
        super(name, display, 1);
        this.catalogue = List.copyOf(catalogue);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        List<WalletFunction> wallets = otherActor.getItemInventoryAs(WalletFunction.class);
        if (wallets.isEmpty()) {
            return actions;
        }

        for (Merchandise merchandise : catalogue) {
            actions.add(new BuyAction(this, merchandise));
        }
        return actions;
    }
}