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

        if (x <= 0 || x >= GameSettings.GAME_WIDHT || y <= 0 || y >= GameSettings.GAME_HEIGHT ) {
            tank.x = new Random().nextInt((int) (GameSettings.GAME_WIDHT*0.7));
            tank.y = new Random().nextInt((int) (GameSettings.GAME_HEIGHT*0.7));
            tank.updateTexture();
        }
    }

}
