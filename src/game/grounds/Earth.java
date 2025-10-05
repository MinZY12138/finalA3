package game.grounds;


import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.Player;
import game.actors.animals.Bear;
import game.actors.animals.Deer;
import game.actors.animals.Spawnable;
import game.actors.animals.Wolf;
import game.grounds.spawnable.Cave;
import game.grounds.spawnable.Meadow;
import game.grounds.spawnable.Tundra;
import game.grounds.teleportable.TeleDoor;
import game.grounds.teleportable.TeleportationCircle;
import game.grounds.trees.AppleTree;
import game.grounds.trees.HazelnutTree;
import game.grounds.trees.YewBerryTree;
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

        TeleDoor teleDoor1 = new TeleDoor(
                List.of(
                        gameMap1.at(10, 4), gameMap2.at(4, 4)
                )
        );
        gameMap1.at(4, 4).setGround(teleDoor1);
        gameMap2.at(10, 4).setGround(teleDoor1);

        TeleportationCircle teleCircle1 = new TeleportationCircle(
                List.of(
                        gameMap1.at(12, 7), gameMap2.at(18, 7)
                )
        );
        gameMap1.at(12, 7).setGround(teleCircle1);
        gameMap2.at(18, 7).setGround(teleCircle1);

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


        gameMap2.at(2, 2).setGround(new AppleTree());
        gameMap1.at(2, 2).setGround(new AppleTree());
        gameMap2.at(6, 7).setGround(new HazelnutTree());
        gameMap1.at(6, 7).setGround(new HazelnutTree());
        gameMap2.at(9, 9).setGround(new YewBerryTree());
        gameMap1.at(9, 9).setGround(new YewBerryTree());

        // req 3
        Location location1 = RandomLocation.randomChooseLocation(gameMap1);
        Location location2 = RandomLocation.randomChooseLocation(gameMap1);
        Location location3 = RandomLocation.randomChooseLocation(gameMap2);

        Axe axe = new Axe(WeaponType.AXE, StatusType.BLEEDING);
        location1.addItem(axe);

        Torch torch = new Torch(WeaponType.TORCH, StatusType.BURNING);
        location2.addItem(torch);

        Bow bow = new Bow(WeaponType.BOW);
        location3.addItem(bow);
    }
}
