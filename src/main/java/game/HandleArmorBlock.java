package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Abilities;
import game.items.equipments.armors.Wearing;

import java.util.List;

public interface HandleArmorBlock
{
    static String handleArmorBlock(Actor target, int damage, GameMap map)
    {
        String returnString = "\nNo block/heal effect (no armor)";

        if (target.hasAbility(Abilities.BLOCK_ATTACK))
        {
            List<Wearing> armor0 = target.getItemInventoryAs(Wearing.class);
            Wearing armor = armor0.get(0);
            int healing = armor.getBlockArmor();
            int remaining = healing - damage;

            if (remaining < 0 && !target.isConscious())
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
