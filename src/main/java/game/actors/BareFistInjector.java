package game.actors;

import edu.monash.fit2099.demo.forest.BareFist;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public interface BareFistInjector {
    default IntrinsicWeapon getNewBareFist(){
        return new BareFist();
    }
}
