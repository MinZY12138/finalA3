package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actors.abilities.Abilities;
import game.actors.animals.Warmable;
import game.weapons.BareFist;

import java.util.List;

/**
 * Class representing the Player.
 *
 * @author Adrian Kristanto
 * {@code @modifiedBy}  Ng Jun Jie, Shee Seng Cheng
 * @version 2.0
 */
public class Player extends Actor implements Warmable {
    private int warmthLevel;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int warmthLevel) {
        super(name, displayChar, hitPoints);
        this.warmthLevel = warmthLevel;
        this.setIntrinsicWeapon(new BareFist());
        this.enableAbility(Abilities.ATTACK);
    }

    /**
     * Decreases the warmth level by 1.
     */
    @Override
    public void decreaseWarmthLevel()
    {
        this.warmthLevel -= 1;
    }

    /**
     * Checks if the actor has positive warmth.
     *
     * @return true if warmthLevel <= 0, false otherwise
     */
    @Override
    public boolean isCold(){
        return warmthLevel <= 0;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        decreaseWarmthLevel();

        if (isCold()) {
            display.println(this + " is unconscious");
            map.removeActor(this);

        }


        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        display.println("Currently at " + map);

        List<Status> statuses = this.statuses();
        for (Status status : statuses) {
            display.println(status.toString());
        }

        display.println(this.showStatus());

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Method to get a String of details of the current player status.
     *
     * @return {@code String} details of this player.
     */
    public String showStatus() {
        return String.
                format("""
                                Player: %s
                                Health: (%s/%s)
                                Warmth Level : %s
                                """,
                        name,
                        getAttribute(BaseAttributes.HEALTH),
                        getMaximumAttribute(BaseAttributes.HEALTH),
                        warmthLevel
                );
    }
}