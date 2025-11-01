package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.HandleArmorBlock;

public abstract class ModifyIntrinsicWeapon extends IntrinsicWeapon
{
    public ModifyIntrinsicWeapon(int damage, String verb, int hitRate, String name)
    {
        super(damage, verb, hitRate, name);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map)
    {
        String message = super.attack(attacker, target, map);
        String attackMissed = "misses";

        if (message.contains(attackMissed)){
            return message;
        }

        return message + HandleArmorBlock.handleArmorBlock(target, this.damage, map);
    }
}
