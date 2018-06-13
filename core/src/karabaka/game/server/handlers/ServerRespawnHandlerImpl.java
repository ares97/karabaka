package karabaka.game.server.handlers;

import karabaka.game.common.entities.Tank;
import karabaka.game.common.handlers.RespawnHandler;
import karabaka.game.common.utils.constants.GameSettings;

import java.util.Random;

public class ServerRespawnHandlerImpl implements RespawnHandler {

    @Override
    public void respawn(Tank tank) {
        int x = (int) tank.x;
        int y = (int) tank.y;

        if (x < -100 || x > GameSettings.GAME_WIDHT + 100 || y < -100 || y > GameSettings.GAME_HEIGHT + 100) {
            tank.x = new Random().nextInt(GameSettings.GAME_WIDHT);
            tank.y = new Random().nextInt(GameSettings.GAME_HEIGHT);
            tank.updateTexture();
        }
    }

}
