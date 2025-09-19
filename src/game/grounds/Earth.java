package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.grounds.teleportable.TeleDoor;
import game.grounds.teleportable.TeleportationCircle;

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

        GameMap gameMap = new GameMap("Forest", groundCreator, map);
        this.addGameMap(gameMap);

        GameMap gameMap1 = new GameMap("Minecraft", groundCreator, map2);
        this.addGameMap(gameMap1);

        TeleDoor teleDoor1 = new TeleDoor(List.of(gameMap.at(10,4),
                gameMap1.at(4,4)));
        gameMap.at(4,4).setGround(teleDoor1);

        gameMap1.at(10,4).setGround(teleDoor1);

        TeleportationCircle teleCircle1 = new TeleportationCircle(List.of(
                gameMap.at(12,7),gameMap1.at(6,7)));

        gameMap.at(6,7).setGround(teleCircle1);

        gameMap1.at(12,7).setGround(teleCircle1);

        Player player = new Player("Explorer", 'ඞ', 100);
        this.addPlayer(player, gameMap.at(1, 1));
    }
}
