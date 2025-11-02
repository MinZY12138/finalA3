package game.grounds;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.Player;
import game.actors.animals.*;
import game.actors.statuses.StatusType;
import game.grounds.spawnable.Cave;
import game.grounds.spawnable.Meadow;
import game.grounds.spawnable.Swamp;
import game.grounds.spawnable.Tundra;
import game.grounds.teleportable.TeleDoor;
import game.grounds.teleportable.TeleportationCircle;
import game.grounds.trees.apples.AppleChild;
import game.grounds.trees.yewBerrys.YewBerryChild;
import game.items.equipments.armors.DiamondArmor;
import game.items.equipments.weapons.Axe;
import game.items.equipments.weapons.Bow;
import game.items.equipments.weapons.Torch;
import game.items.equipments.weapons.WeaponType;
import game.items.fruits.*;
import game.items.TeleportCube;

import java.util.Arrays;
import java.util.List;


public class Earth extends World {

    public Earth(Display display) {
        super(display);
    }

    public void constructWorld() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Snow::new);
        groundCreator.registerGround('/', Glass::new);

        List<String> map = Arrays.asList(
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................"
        );

        List<String> map2 = Arrays.asList(
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................"
        );

        // 6*25
        List<String> store1 = Arrays.asList(

                "//////////////...........",
                "/............/...........",
                "//////////////...........",
                ".........................",
                ".........................",
                "........................."
        );
        // 6*25
        List<String> store2 = Arrays.asList(

                "//////////////...........",
                "/............/...........",
                "//////////////...........",
                ".........................",
                ".........................",
                "........................."
        );
        // 6*25
        List<String> store3 = Arrays.asList(

                "//////////////...........",
                "/............/...........",
                "//////////////...........",
                ".........................",
                ".........................",
                "........................."
        );

        //Req5
        GameMap mysteriousStore1 = new GameMap("Mysterious Store Armor", groundCreator, store1);
        GameMap mysteriousStore2 = new GameMap("Mysterious Store Weapon", groundCreator, store2);
        GameMap mysteriousStore3 = new GameMap("Mysterious Store Fruit", groundCreator, store3);


        GameMap forest = new GameMap("Forest", groundCreator, map);
        this.addGameMap(forest);

        Player player = new Player("Explorer", 'ඞ', 100, 30);
        this.addPlayer(player, forest.at(1, 1));

        // req 1
        GameMap plains = new GameMap("Plains", groundCreator, map2);
        this.addGameMap(plains);

        Location locationDoor1 = forest.at(4,4);
        TeleDoor teleDoor1 = new TeleDoor(
                List.of(
                        forest.at(10, 4), plains.at(4, 4)
                ), locationDoor1
        );
        locationDoor1.setGround(teleDoor1);

        Location locationDoor2 = plains.at(4,4);
        TeleDoor teleDoor2 = new TeleDoor(
                List.of(
                        forest.at(4, 4), plains.at(10, 4)
                ), locationDoor2
        );
        locationDoor2.setGround(teleDoor2);

        Location locationCricle1 = forest.at(12, 7);
        TeleportationCircle teleCircle1 = new TeleportationCircle(
                List.of(
                        forest.at(18, 7), plains.at(12, 7)
                ), locationCricle1
        );
        locationCricle1.setGround(teleCircle1);

        Location locationCircle2 = plains.at(12,7);
        TeleportationCircle teleCircle2 = new TeleportationCircle(
                List.of(
                        plains.at(18,7), forest.at(12,7)
                ),
                locationCircle2
        );
        locationCircle2.setGround(teleCircle2);


        TeleportCube cube1 = new TeleportCube(
                List.of(
                        forest.at(1, 1), plains.at(1, 1)
                )
        );

        player.addItemToInventory(cube1);

        // req 2
        Spawnable bear = Bear::new;
        Spawnable deer = Deer::new;
        Spawnable wolf = Wolf::new;
        Spawnable crocodile = Crocodile::new;

        forest.at(8, 9).setGround(new Tundra(List.of(bear)));
        plains.at(0, 4).setGround(new Tundra(List.of(crocodile)));
        forest.at(0, 5).setGround(new Cave(List.of(wolf)));
        plains.at(0, 5).setGround(new Cave(List.of(bear, wolf)));
        forest.at(0, 1).setGround(new Meadow(List.of(crocodile)));
        plains.at(0, 9).setGround(new Meadow(List.of(deer, wolf)));

        forest.at(10, 0).setGround(new Swamp(List.of(deer)));
        plains.at(7, 9).setGround(new Swamp(List.of(crocodile)));


        plains.at(2, 2).addItem(new Apple());
        forest.at(2, 2).addItem(new Apple());
        plains.at(6, 7).addItem(new Hazelnut());
        forest.at(6, 7).addItem(new Hazelnut());
        plains.at(9, 9).addItem(new YewBerry());
        forest.at(9, 9).addItem(new YewBerry());

        // req 3
        Location location1 = RandomLocation.randomChooseLocation(forest);
        Location location2 = RandomLocation.randomChooseLocation(forest);
        Location location3 = RandomLocation.randomChooseLocation(plains);

        Axe axe = new Axe(WeaponType.AXE, StatusType.BLEEDING);
        location1.addItem(axe);

        Torch torch = new Torch(WeaponType.TORCH, StatusType.BURNING);
        location2.addItem(torch);

        Bow bow = new Bow(WeaponType.BOW, null);
        location3.addItem(bow);

        // Req 1 (AS3)
        //----Apple----
        // Forest
        forest.at(7,0).setGround(AppleChild.createAppleSprouts(false,true));

        // Plains
        plains.at(0,0).setGround(AppleChild.createSkipSaplingAppleSprouts(true));

        //----YewBerry----
        //Forest
        forest.at(19,0).setGround(YewBerryChild.createYewBerrySapling(false));

        //Plains
        plains.at(5,0).setGround(YewBerryChild.createYewBerrySapling(true));

        forest.locationOf(player).addItem(new DiamondArmor());
    }
}
