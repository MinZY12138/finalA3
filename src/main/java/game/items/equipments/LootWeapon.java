package game.items.equipments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.HandleArmorBlock;
import game.actions.AttackAction;

import java.util.Random;

/**
 * <h1>LootWeapon class</h1>
 * <p>
 * The {@code LootWeapon} is an {@link Item} and {@link Weapon} at the same time (i.e. equipment).
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 3.0
 * @since 2025-09-24
 */
public abstract class LootWeapon extends Item implements Weapon
{

    /**
     * Defining weapon attributes.
     */
    protected final WeaponType TYPE;

    /**
     * Defining weapon status.
     */
    protected final StatusType EFFECT;

    /**
     * A random object.
     */
    protected static final Random RAND = new Random();

    /**
     * The constructor of the LootWeapon class.
     *
     * @param name        the name of a weapon
     * @param displayChar the symbol of the weapon
     * @param portable    determine if a weapon is portable
     * @param type        the basic attributes of a weapon
     * @param effect      the status of a weapon
     */
    public LootWeapon(String name, char displayChar, boolean portable, WeaponType type,
                      StatusType effect)
    {
        super(name, displayChar, portable);
        this.TYPE = type;
        this.EFFECT = effect;
    }

    /**
     * Attack a target actor with additional effects.
     *
     * @param attacker represent an actor attack
     * @param target   represent an actor being attacked
     * @param map      the game map
     */
    public void hit(Actor attacker, Actor target, GameMap map)
    {
    }

    /**
     * Define a weapon hit rate and show a description of the action.
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a string message
     */
    @Override
    public final String attack(Actor attacker, Actor target, GameMap map)
    {
        int maximumBound = 100;

        if (!(RAND.nextInt(maximumBound) <= this.getHitRate()))
        {
            return attacker + " misses " + target + ".";
        }

        target.hurt(this.getDamage());
        this.hit(attacker, target, map);

        String handleBlockInfo =
                HandleArmorBlock.handleArmorBlock(target, this.getDamage(), map);

        return String.format("%s %s %s for %d damage%s",
                attacker, this.getVerb(), target, this.getDamage(), handleBlockInfo);
    }

    /**
     * Represent a weapon what action is allowable.
     *
     * @param otherActor the other actor
     * @param location   the location of the other actor
     * @return a list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location)
    {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new AttackAction(otherActor, location.toString(),
                this.getVerb(), this));
        return actions;
    }

    /**
     * The accessor of the weapon damage.
     *
     * @return this weapon damage
     */
    public int getDamage()
    {
        return TYPE.getDAMAGE();
    }

    /**
     * The accessor of the chance of hitting target.
     *
     * @return this weapon hit rate
     */
    public int getHitRate()
    {
        return TYPE.getHIT_RATE();
    }

    /**
     * The accessor of the word to describe the weapon when hitting the target.
     *
     * @return this weapon hitting description
     */
    public String getVerb()
    {
        return TYPE.getVERB();
    }
}

