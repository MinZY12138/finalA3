package game.actors.statuses;

import edu.monash.fit2099.engine.actors.Actor;

public class Bleeding extends ContinuousDamage {

    public Bleeding(Actor actor, int damage, int duration) {
        super(actor, damage, duration, "is bleeding");
    }
}
