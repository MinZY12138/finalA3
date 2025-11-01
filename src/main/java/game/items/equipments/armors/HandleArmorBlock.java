package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Abilities;

import java.util.List;

/**
 * <h1>HandleArmorBlock Interface</h1>
 * <p>
 * The {@code HandleArmorBlock} interface responsible for applying block, healing, and
 * failure effects based on the equipped armor's defensive capabilities.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public interface HandleArmorBlock
{
    /**
     * Evaluates how armor mitigates incoming damage and applies corresponding effects
     * such as blocking or healing. this method determines whether the armor successfully
     * absorbed the attack, failed to protect the actor, or provided healing through overblocking.
     *
     * @param target the actor receiving the attack
     * @param damage the amount of damage dealt by the attacker
     * @param map    the game map where the combat takes place
     * @return a formatted message describing the result of the armor's effect
     */
    static String handleArmorBlock(Actor target, int damage, GameMap map)
    {
        String returnString = "\nNo block/heal effect (no armor)";

        if (target.hasAbility(Abilities.BLOCK_ATTACK))
        {
            List<Wearing> armor0 = target.getItemInventoryAs(Wearing.class);

            int firstElement = 0;
            Wearing armor = armor0.get(firstElement);

            int healing = armor.getBlockArmor();
            int remaining = healing - damage;

            int lowerBound = 0;

            if (remaining < lowerBound && !target.isConscious())
            {
                target.unconscious(map);
                returnString = "\n" + armor.getSimpleArmorInfo() + " failed to protect " + target;
            } else
            {
                target.heal(armor.getBlockArmor());

                if (healing <= damage)
                {
                    returnString = "\n" + target + " has block  " + healing + " damage due to " +
                            armor.getSimpleArmorInfo();
                } else
                {
                    returnString = "\n" + target + " has heal back " + remaining + " hitpoints due to " +
                            armor.getSimpleArmorInfo();
                }
            }
        }

        return returnString;
    }
}
