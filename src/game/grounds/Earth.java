package game.grounds;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.Player;
import game.actors.animals.*;
import game.grounds.spawnable.Cave;
import game.grounds.spawnable.Meadow;
import game.grounds.spawnable.Tundra;
import game.grounds.teleportable.TeleDoor;
import game.grounds.teleportable.TeleportationCircle;
import game.grounds.trees.apples.AppleSapling;
import game.grounds.trees.apples.AppleSprouts;
import game.grounds.trees.apples.AppleTree;
import game.grounds.trees.Growthable;
import game.grounds.trees.yewBerrys.YewBerrySapling;
import game.grounds.trees.yewBerrys.YewBerryTree;
import game.items.fruits.*;
import game.items.TeleportCube;
import game.items.equipments.*;

import java.util.Arrays;
import java.util.List;


public class Earth extends World {

    public Earth(Display display) {
        super(display);
    }

    public void constructWorld() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Snow::new);

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

        forest.at(8, 9).setGround(new Tundra(List.of(bear)));
        plains.at(0, 0).setGround(new Tundra(List.of(wolf)));
        forest.at(0, 5).setGround(new Cave(List.of(bear, wolf, deer)));
        plains.at(0, 5).setGround(new Cave(List.of(bear, wolf)));
        forest.at(0, 9).setGround(new Meadow(List.of(deer)));
        plains.at(0, 9).setGround(new Meadow(List.of(deer, wolf)));


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
        //(Connasences of execution)
        //----Apple----
        // Forest
        AppleTree matureAppleTreeF = new AppleTree();

        Growthable appleSaplingF = new AppleSapling(true);
        appleSaplingF.setNextStage(matureAppleTreeF);

        Growthable appleSproutsF = new AppleSprouts(false);
        appleSproutsF.setNextStage(appleSaplingF);

        forest.at(0,0).setGround(appleSproutsF);

        // Plains
        AppleTree matureAppleTreeP = new AppleTree();

        Growthable appleSproutsP = new AppleSprouts(true);
        appleSproutsP.setNextStage(matureAppleTreeP);
        plains.at(0,0).setGround(appleSproutsP);
        //----YewBerry----
        //Forest
        YewBerryTree matureYewBerryF = new YewBerryTree();

        Growthable yewBerrySaplingF = new YewBerrySapling(false);
        yewBerrySaplingF.setNextStage(matureYewBerryF);

        forest.at(5,0).setGround(yewBerrySaplingF);

        //Plains
        YewBerryTree matureYewBerryP = new YewBerryTree();

        Growthable yewBerrySaplingP = new YewBerrySapling(true);
        yewBerrySaplingP.setNextStage(matureYewBerryP);

        plains.at(5,0).setGround(yewBerrySaplingP);

    }
}
