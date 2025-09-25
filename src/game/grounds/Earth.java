package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.Player;
import game.grounds.teleportable.TeleDoor;
import game.grounds.teleportable.TeleportationCircle;
import game.items.TeleportCube;
import game.items.equipments.Axe;
import game.items.equipments.Torch;
import game.items.equipments.Bow;

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

        GameMap gameMap2 = new GameMap("Minecraft", groundCreator, map2);
        this.addGameMap(gameMap2);

        TeleDoor teleDoor1 = new TeleDoor(List.of(gameMap1.at(10,4),
                gameMap2.at(4,4)));
        gameMap1.at(4,4).setGround(teleDoor1);
        gameMap2.at(10,4).setGround(teleDoor1);

        TeleportationCircle teleCircle1 = new TeleportationCircle(List.of(
                gameMap1.at(12,7),gameMap2.at(6,7)));
        gameMap1.at(6,7).setGround(teleCircle1);
        gameMap2.at(12,7).setGround(teleCircle1);

        TeleportCube cube1 = new TeleportCube(List.of(
                gameMap1.at(1,1), gameMap2.at(1,1)));

        Player player = new Player("Explorer", 'ඞ', 100);
        this.addPlayer(player, gameMap1.at(1, 1));
        player.addItemToInventory(cube1);

        Location gameMapA = RandomLocation.randomChooseLocation(gameMap1);
        Location gameMapB = RandomLocation.randomChooseLocation(gameMap1);

        Axe axe = new Axe();
        gameMapA.addItem(axe);

        Torch torch = new Torch();
        gameMapA.addItem(torch);

        Bow bow = new Bow();
        gameMapB.addItem(bow);
    }
}
