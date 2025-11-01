package game.behaviours;

import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * <h1>AttackBehaviourInjector Interface</h1>
 * <p>
 *     The {@code AttackBehaviourInjector} acts an injector to avoid a concrete class depending
 *     on a concrete class.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-11-01
 */
public interface AttackBehaviourInjector {

    /**
     * Creates and returns a new instance.
     *
     * @return a new AttackBehaviour instance
     */
    static Behaviour getAttackBehaviour() {
        return new AttackBehaviour();
    }
}
