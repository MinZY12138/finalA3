package game.mysteriostore;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.RandomLocation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an open dimensional store linked to a particular portal location.
 */
public final class MysterioStoreSession {

    private final MysterioStoreInterior store;
    private final Location portalLocation;
    private final Map<Actor, Location> visitors;
    private boolean open;

    MysterioStoreSession(MysterioStoreInterior store, Location portalLocation) {
        this.store = Objects.requireNonNull(store);
        this.portalLocation = Objects.requireNonNull(portalLocation);
        this.visitors = new LinkedHashMap<>();
        this.open = true;
    }

    public boolean isOpen() {
        return open;
    }

    public String enter(Actor actor) {
        if (!open) {
            return actor + " finds that the " + store.name() + " has vanished.";
        }
        if (visitors.containsKey(actor)) {
            return actor + " is already inside the " + store.name() + ".";
        }

        Location entrance = store.entrance();
        Location returnLocation = chooseReturnLocation(actor);

        visitors.put(actor, returnLocation);
        portalLocation.map().moveActor(actor, entrance);

        return actor + " steps into the " + store.name() + ", greeted by " + store.seller() + ".";
    }

    public String leave(Actor actor) {
        Location returnLocation = visitors.remove(actor);
        if (returnLocation == null) {
            return actor + " is not browsing the " + store.name() + ".";
        }

        GameMap destinationMap = returnLocation.map();
        destinationMap.moveActor(actor, returnLocation);
        return actor + " leaves the " + store.name() + " and reappears at " + returnLocation + ".";
    }

    public void forceClose() {
        if (!open) {
            return;
        }
        open = false;
        List<Actor> exiting = new ArrayList<>(visitors.keySet());
        for (Actor actor : exiting) {
            leave(actor);
        }
        visitors.clear();
        store.sessionClosed(this);
    }

    public String storeName() {
        return store.name();
    }

    Location portalLocation() {
        return portalLocation;
    }

    private Location chooseReturnLocation(Actor actor) {
        GameMap originalMap = portalLocation.map();
        List<Location> attempts = new ArrayList<>();

        for (int tries = 0; tries < 20; tries++) {
            Location candidate = RandomLocation.randomChooseLocation(originalMap);
            attempts.add(candidate);
            if (!candidate.containsAnActor() && candidate.canActorEnter(actor)) {
                return candidate;
            }
        }

        for (Location fallback : attempts) {
            if (!fallback.containsAnActor() && fallback.canActorEnter(actor)) {
                return fallback;
            }
        }

        for (int x : originalMap.getXRange()) {
            for (int y : originalMap.getYRange()) {
                Location candidate = originalMap.at(x, y);
                if (!candidate.containsAnActor() && candidate.canActorEnter(actor)) {
                    return candidate;
                }
            }
        }

        return portalLocation;
    }
}