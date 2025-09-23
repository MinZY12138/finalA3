package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
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
import game.items.TeleportCube;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        GameMap gameMap = new GameMap("Forest", groundCreator, map);
        this.addGameMap(gameMap);

        GameMap gameMap1 = new GameMap("Plains", groundCreator, map2);
        this.addGameMap(gameMap1);

        TeleDoor teleDoor1 = new TeleDoor(List.of(gameMap.at(10,4),
                gameMap1.at(4,4)));
        gameMap.at(4,4).setGround(teleDoor1);

        gameMap1.at(10,4).setGround(teleDoor1);

        TeleportationCircle teleCircle1 = new TeleportationCircle(List.of(
                gameMap.at(12,7),gameMap1.at(6,7)));

        gameMap.at(6,7).setGround(teleCircle1);

        gameMap1.at(12,7).setGround(teleCircle1);

        TeleportCube cube1 = new TeleportCube(List.of(gameMap.at(1,1), gameMap1.at(1,1)));

        Player player = new Player("Explorer", 'ඞ', 100, 30);
        this.addPlayer(player, gameMap.at(1, 1));
        player.addItemToInventory(cube1);

        Spawnable bear = Bear::new;
        Spawnable deer = Deer::new;
        Spawnable wolf = Wolf::new;


        gameMap.at(8,9).setGround(new Tundra(bear));

        gameMap1.at(0,0).setGround(new Tundra(wolf));
        gameMap.at(0,5).setGround(new Cave(bear, wolf, deer));
        gameMap1.at(0,5).setGround(new Cave(bear, wolf));
        gameMap.at(0,9).setGround(new Meadow(deer));
        gameMap1.at(0,9).setGround(new Meadow(deer,wolf));

        gameMap.at(2,2).setGround(new AppleTree());
        gameMap1.at(2,2).setGround(new AppleTree());
        gameMap.at(6,7).setGround(new HazelnutTree());
        gameMap1.at(6,7).setGround(new HazelnutTree());
        gameMap.at(9,9).setGround(new YewBerryTree());
        gameMap1.at(9,9).setGround(new YewBerryTree());




    }
}
