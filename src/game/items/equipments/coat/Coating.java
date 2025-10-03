package game.items.equipments.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Coating {
    String name();
    void applyOnHit(Actor attacker, Actor target, GameMap map);
}
