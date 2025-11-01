package game.items.equipments.armors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.ItemInfo;

public class ArmorHolder extends Item implements Wearing
{
    private Armor armor;

    private final Actor ACTOR;

    public ArmorHolder(Actor actor){
        super(ItemInfo.ARMOR_HOLDER.getNAME(),
                ItemInfo.ARMOR_HOLDER.getCHAR(),
                ItemInfo.ARMOR_HOLDER.isPORTABLE());
        this.ACTOR = actor;
    }

    @Override
    public String setArmor(Armor armor)
    {
        this.armor = armor;
        return ACTOR + " has wear " + armor;
    }

    @Override
    public int getBlockArmor()
    {
        return this.armor.getDEFENSE();
    }

    @Override
    public String getSimpleArmorInfo(){
        return armor.getName() + " " + armor.getFunctionality();
    }

    @Override
    public String getArmorInfo()
    {
        String returnString = "No armor yet";

        if (armor!= null){
            returnString = armor.toString();
        }

        return returnString;
    }
}
