package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>ArmorHolderInjector Interface</h1>
 * <p>
 * The {@code ArmorHolderInjector} acts an injector to avoid a concrete class depending on a concrete class.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public interface ArmorHolderInjector
{
    /**
     * Factory method that creates an ArmorHolder for the specified actor.
     *
     * @param target the actor for whom the armor holder is created
     * @return a new ArmorHolder instance
     */
    static ArmorHolder createArmorHolder(Actor target)
    {
        return new ArmorHolder(target);
    }
}
