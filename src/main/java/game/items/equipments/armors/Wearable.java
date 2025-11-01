package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;

public interface Wearable {

    String wornBy(Actor actor, Wearing armorHolder);
}
