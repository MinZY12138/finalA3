package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * <h1>BareFistInjector Interface</h1>
 * <p>
 * The {@code BareFistInjector} acts an injector to avoid a concrete class depending on a
 * concrete class.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-10-31
 */
public interface BareFistInjector {

    /**
     * Creates and returns a new instance.
     *
     * @return a new BareFist instance
     */
    static IntrinsicWeapon getNewBareFist(){
        return new BareFist();
    }
}
