package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Wearable Interface</h1>
 * <p>
 *     The {@code Wearable} represents an item can be worn if implementing this interface.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public interface Wearable {

    /**
     * Wear this armor onto the actor.
     *
     * @param actor       the actor who wear the armor
     * @param armorHolder the management of the equipped armor state
     * @return a string describing the result of the wear action
     */
    String wornBy(Actor actor, Wearing armorHolder);
}
