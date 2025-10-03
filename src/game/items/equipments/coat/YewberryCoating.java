package game.items.equipments.coat;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.statuses.Poisoning;

/**
 * Coating that poisons the target for 5 turns (-4 HP per turn).
 */
public class YewberryCoating implements Coating {

    @Override
    public String name() {
        return "Yewberry";
    }

    @Override
    public void applyOnHit(Actor attacker, Actor target, GameMap map) {
        // Attach Poisoning status to target
        target.addStatus(new Poisoning(target, 5));
    }
}
