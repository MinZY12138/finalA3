package game.items.equipments;

public class Axe extends LootWeapon {

    public Axe(String name, char displayChar, boolean portable, int damage, int hitRate,
               String verb) {
        super("axe", 'p', true, 15, 75, "hacks");
    }
}