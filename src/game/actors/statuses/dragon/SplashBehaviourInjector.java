package game.actors.statuses.dragon;

import game.behaviours.SplashBehaviour;

/**
 * <h1>Interface SplashBehaviourInjector</h1>
 *
 * Act as an injector to inject SplashBehaviour.
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface SplashBehaviourInjector
{
    /**
     * Method to return a new SplashBehaviour
     * @return {@link SplashBehaviour} object
     */
    default SplashBehaviour newBehaviourSplash()
    {
        return new SplashBehaviour();
    }
}
