package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * <h1>Attack Action class</h1>
 * <p>
 * The {@code AttackAction} is an {@link Action} that allows {@link Actor} to attack others.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-09-05
 */
public class AttackAction extends Action {

    /**
     * The actor is being attacked.
     */
    private final Actor TARGET_ACTOR;

    /**
     * The actor's direction being attacked.
     */
    private final String DIRECTION;

    private final String VERB;

    private final Weapon WEAPON;

    /**
     * The constructor of the AttackAction class.
     *
     * @param targetActor the actor is being attacked.
     * @param direction   the actor's direction being attacked.
     */
    public AttackAction(Actor targetActor, String direction, String verb, Weapon weapon) {
        this.TARGET_ACTOR = targetActor;
        this.DIRECTION = direction;
        this.VERB = verb;
        this.WEAPON = weapon;
    }

    /**
     * The given actor performs the attack action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string message about who is being attacked
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String description = this.WEAPON.attack(actor, TARGET_ACTOR, map);

        if (!TARGET_ACTOR.isConscious()) {
            description += "\n" + TARGET_ACTOR.unconscious(actor, map);
        }

        return description;
    }

    /**
     * Describe the attack action will be performed in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + this.VERB + " " + this.TARGET_ACTOR + " with " + this.WEAPON;
    }
}
