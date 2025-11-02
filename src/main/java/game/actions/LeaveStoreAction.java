package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mysteriostore.MysterioStoreSession;

import java.util.Objects;

/**
 * Action that allows an actor to leave an active Mysterio store, returning to the original map.
 */
public final class LeaveStoreAction extends Action {

    private final MysterioStoreSession session;

    public LeaveStoreAction(MysterioStoreSession session) {
        this.session = Objects.requireNonNull(session);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return session.leave(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Leave the " + session.storeName();
    }
}