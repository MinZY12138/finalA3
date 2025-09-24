package game.items.equipments;

public class Bow extends LootWeapon {

    public Bow(String name, char displayChar, boolean portable, int damage, int hitRate,
               String verb) {
        super("bow", 'c', true, 5, 25, "shhots");
    }
}
