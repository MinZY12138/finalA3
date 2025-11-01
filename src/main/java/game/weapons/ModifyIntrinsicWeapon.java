package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.equipments.armors.HandleArmorBlock;

/**
 * <h1>ModifyIntrinsicWeapon class</h1>
 * <p>
 * The {@code ModifyIntrinsicWeapon} is an abstract extension of {@link IntrinsicWeapon}
 * that introduces additional post-attack logic to interact with the armor system.
 * It integrates the {@link HandleArmorBlock} mechanism to apply blocking or healing
 * effects when an attack successfully lands on target wearing armor.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public abstract class ModifyIntrinsicWeapon extends IntrinsicWeapon
{
    /**
     * Constructor of the ModifyIntrinsicWeapon class.
     *
     * @param damage  the mount of damage this weapon deals
     * @param verb    the verb describing the attack
     * @param hitRate the chance that the attack will hit
     * @param name    the name of the intrinsic weapon
     */
    public ModifyIntrinsicWeapon(int damage, String verb, int hitRate, String name)
    {
        super(damage, verb, hitRate, name);
    }

    /**
     * Executes the attack action with armor-block modification logic.
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a string describing the full result of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map)
    {
        String message = super.attack(attacker, target, map);
        String attackMissed = "misses";

        if (message.contains(attackMissed))
        {
            return message;
        }

        return message + HandleArmorBlock.handleArmorBlock(target, this.damage, map);
    }
}
