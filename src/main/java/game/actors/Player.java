package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actors.animals.Warmable;
import game.items.currency.WalletFunction;
import game.items.equipments.armors.ArmorHolderInjector;
import game.items.equipments.armors.Wearing;

import java.util.List;

/**
 * Class representing the Player.
 *
 * @author Adrian Kristanto
 * {@code @modifiedBy}  Ng Jun Jie, Shee Seng Cheng
 * @version 2.0
 */
public class Player extends Actor implements Warmable
{

    /**
     * Player's warmth level.
     */
    private int warmthLevel;

    /**
     * Constructor of the Player class.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int warmthLevel)
    {
        super(name, displayChar, hitPoints);
        this.warmthLevel = warmthLevel;
        setIntrinsicWeapon(BareFistInjector.getNewBareFist());
        enableAbility(Abilities.ATTACK);
        addItemToInventory(ArmorHolderInjector.createArmorHolder(this));
        addItemToInventory(WalletInjector.getNewWallet());
    }

    /**
     * Decreases the warmth level by 1.
     */
    @Override
    public void decreaseWarmthLevel()
    {
        if (!hasAbility(Abilities.COLD_RESISTANT))
        {
            this.warmthLevel--;
        }
    }

    /**
     * Checks if the actor has positive warmth.
     *
     * @return true if warmthLevel <= 0, false otherwise
     */
    @Override
    public boolean isCold()
    {
        return warmthLevel <= 0;
    }

    /**
     * Managing player state in each turn in the game.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     *                   interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display)
    {
        if (!this.isConscious())
        {
            display.println(this.unconscious(map));
            return new DoNothingAction();
        }

        decreaseWarmthLevel();

        if (isCold())
        {
            display.println(this + " is unconscious, too cool...");
            this.unconscious(map);
            return new DoNothingAction();
        }

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        display.println("Currently at " + map);
        display.println(this.showStatus());

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    private String getBalance() {
        String returnString = "";
        List<WalletFunction> wallet = this.getItemInventoryAs(WalletFunction.class);

        //Always get the first wallet occurrence
        if (!wallet.isEmpty()) {
            int firstElement = 0;
            returnString += wallet.get(firstElement).showBalance();
        }
        return returnString;
    }

    private String getArmorInfo(){
        String armorInfo = "No armor yet";
        List<Wearing> armorHolder = getItemInventoryAs(Wearing.class);

        if (!armorHolder.isEmpty())
        {
            int firstElement = 0;
            armorInfo = armorHolder.get(firstElement).getArmorInfo();
        }
        return armorInfo;
    }

    /**
     * Method to get a String of details of the current player status.
     *
     * @return {@code String} details of this player.
     */
    public String showStatus()
    {
        return String.
                format("""
                                Player: %s
                                Health: (%s/%s)
                                Warmth Level : %s
                                Wallet: %s
                                Armor Info : %s
                                """,
                        name,
                        getAttribute(BaseAttributes.HEALTH),
                        getMaximumAttribute(BaseAttributes.HEALTH),
                        warmthLevel,
                        getBalance(),
                        getArmorInfo()
                );
    }
}