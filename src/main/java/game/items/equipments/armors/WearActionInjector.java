package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.WearAction;

/**
 * <h1>WearActionInjector Interface</h1>
 * <p>
 * The {@code WearActionInjector} acts an injector to avoid a concrete class depending on a concrete class.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public interface WearActionInjector
{

    /**
     * Factory method that creates a WearAction for equipping an armor item.
     *
     * @param armor       the wearable armor to be equipped
     * @param armorHolder the holder managing the equipped armor state
     * @param target      the actor who wear the armor
     * @return a new instance of the WearAction
     */
    static WearAction createWearAction(Wearable armor, Wearing armorHolder, Actor target)
    {
        return new WearAction(armor, armorHolder, target);
    }
}
