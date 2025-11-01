package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.WearAction;

public interface WearActionInjector {

    default WearAction createWearAction(Wearable armor, Wearing armorHolder, Actor target){
        return new WearAction(armor, armorHolder, target);
    }
}
