package game.items.equipments;

public class Torch extends LootWeapon {

    public Torch(String name, char displayChar, boolean portable, int damage, int hitRate,
                 String verb) {
        super("torch", 'y', true, 10, 50, "strikes");
    }
}
