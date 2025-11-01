package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;

public interface ArmorHolderInjector
{
    static ArmorHolder createArmorHolder(Actor target) {
        return new ArmorHolder(target);
    }
}
