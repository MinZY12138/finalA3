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


        GameMap gameMap1 = new GameMap("Forest", groundCreator, map);
        this.addGameMap(gameMap1);

        Player player = new Player("Explorer", 'ඞ', 100, 30);
        this.addPlayer(player, gameMap1.at(1, 1));

        // req 1
        GameMap gameMap2 = new GameMap("Plains", groundCreator, map2);
        this.addGameMap(gameMap2);

        Location locationDoor1 = gameMap1.at(4,4);
        TeleDoor teleDoor1 = new TeleDoor(
                List.of(
                        gameMap1.at(10, 4), gameMap2.at(4, 4)
                ), locationDoor1
        );
        locationDoor1.setGround(teleDoor1);

        Location locationDoor2 = gameMap2.at(4,4);
        TeleDoor teleDoor2 = new TeleDoor(
                List.of(
                        gameMap1.at(4, 4), gameMap2.at(10, 4)
                ), locationDoor2
        );
        locationDoor2.setGround(teleDoor2);

        Location locationCricle1 = gameMap1.at(12, 7);
        TeleportationCircle teleCircle1 = new TeleportationCircle(
                List.of(
                        gameMap1.at(18, 7), gameMap2.at(12, 7)
                ), locationCricle1
        );
        locationCricle1.setGround(teleCircle1);

        Location locationCircle2 = gameMap2.at(12,7);
        TeleportationCircle teleCircle2 = new TeleportationCircle(
                List.of(
                        gameMap2.at(18,7), gameMap1.at(12,7)
                ),
                locationCircle2
        );
        locationCircle2.setGround(teleCircle2);


        TeleportCube cube1 = new TeleportCube(
                List.of(
                        gameMap1.at(1, 1), gameMap2.at(1, 1)
                )
        );

        player.addItemToInventory(cube1);

        // req 2
        Spawnable bear = Bear::new;
        Spawnable deer = Deer::new;
        Spawnable wolf = Wolf::new;

        gameMap1.at(8, 9).setGround(new Tundra(List.of(bear)));
        gameMap2.at(0, 0).setGround(new Tundra(List.of(wolf)));
        gameMap1.at(0, 5).setGround(new Cave(List.of(bear, wolf, deer)));
        gameMap2.at(0, 5).setGround(new Cave(List.of(bear, wolf)));
        gameMap1.at(0, 9).setGround(new Meadow(List.of(deer)));
        gameMap2.at(0, 9).setGround(new Meadow(List.of(deer, wolf)));


        gameMap2.at(2, 2).addItem(new Apple());
        gameMap1.at(2, 2).addItem(new Apple());
        gameMap2.at(6, 7).addItem(new Hazelnut());
        gameMap1.at(6, 7).addItem(new Hazelnut());
        gameMap2.at(9, 9).addItem(new YewBerry());
        gameMap1.at(9, 9).addItem(new YewBerry());

        // req 3
        Location location1 = RandomLocation.randomChooseLocation(gameMap1);
        Location location2 = RandomLocation.randomChooseLocation(gameMap1);
        Location location3 = RandomLocation.randomChooseLocation(gameMap2);

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

        gameMap1.at(0,0).setGround(appleSproutsF);

        // Plains
        AppleTree matureAppleTreeP = new AppleTree();

        Growthable appleSproutsP = new AppleSprouts(true);
        appleSproutsP.setNextStage(matureAppleTreeP);
        gameMap2.at(0,0).setGround(appleSproutsP);
        //----YewBerry----
        //Forest
        YewBerryTree matureYewBerryF = new YewBerryTree();

        Growthable yewBerrySaplingF = new YewBerrySapling(false);
        yewBerrySaplingF.setNextStage(matureYewBerryF);

        gameMap1.at(5,0).setGround(yewBerrySaplingF);

        //Plains
        YewBerryTree matureYewBerryP = new YewBerryTree();

        Growthable yewBerrySaplingP = new YewBerrySapling(true);
        yewBerrySaplingP.setNextStage(matureYewBerryP);

        gameMap2.at(5,0).setGround(yewBerrySaplingP);

    }
}
