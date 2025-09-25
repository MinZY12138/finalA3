package game.actors.animals;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import edu.monash.fit2099.engine.items.*;
import game.behaviours.*;
import game.items.fruits.Consumable;

import java.util.ArrayList;

public abstract class Animal extends Actor implements Warmable {

    private GameMap currentMap;
    private int warmthLevel;
    public boolean resistanceToWarm;
    private final WanderBehaviour wanderBehaviour = new WanderBehaviour();
    private final ConsumeBehaviour consumeBehaviour = new ConsumeBehaviour();

    public Animal(String name, char displayChar, int hitpoints, int warmthLevel) {
        super(name, displayChar, hitpoints);
        this.warmthLevel = warmthLevel;
        this.resistanceToWarm = false;
    }

    @Override
    public void decreaseWarmthLevel() {
        this.warmthLevel -= 1;
    }


    @Override
    public boolean isConscious() {
        if (warmthLevel <= 0) {
            this.hurt(this.getAttribute(BaseAttributes.HEALTH));
        }
        return super.isConscious();
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.currentMap = map;

        if (!isConscious()) {
            display.println(this + " is unconscious due to warmth level");
            map.removeActor(this);
            return new DoNothingAction();


        }

        if (!resistanceToWarm){
            decreaseWarmthLevel();
        }




        Location place = map.locationOf(this);
        for(Item item: new ArrayList<>(place.getItems())){
            if (item instanceof Consumable consumable){

                return consumeBehaviour.generateAction(this, map);

            }

        }
        return wanderBehaviour.generateAction(this, map);

    }

    public String toString() {


        Location location = currentMap.locationOf(this);

        return "At "+ location + ", " + super.toString() + " ( warmth level: " + this.warmthLevel + " ) ";
    }
}
