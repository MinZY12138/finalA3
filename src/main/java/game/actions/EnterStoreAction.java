package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mysteriostore.MysterioStoreSession;

import java.util.Objects;

/**
 * Action allowing an actor to enter an active dimensional store.
 */
public final class EnterStoreAction extends Action {

    private final MysterioStoreSession session;

    public EnterStoreAction(MysterioStoreSession session) {
        this.session = Objects.requireNonNull(session);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return session.enter(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Enter the " + session.storeName();
    }
}