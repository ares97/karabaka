package karabaka.game.server.handlers;

import karabaka.game.common.entities.EntityContainer;
import karabaka.game.common.entities.Tank;
import karabaka.game.common.handlers.MoveHandler;
import karabaka.game.common.utils.constants.Direction;
import karabaka.game.common.utils.constants.GameSettings;

public class ServerMoveHandlerImpl implements MoveHandler {

    private final Object lock = new Object();

    @Override
    public void move(Direction direction, Tank player) {
        synchronized (lock) {
            float lastX = player.x;
            float lastY = player.y;

            updatePosition(direction, player);
            player.setDirection(direction);

            for (Tank tank : EntityContainer.instance.getTanks()) {
                if (tank != player && tank.overlaps(player)) {
                    player.x = lastX;
                    player.y = lastY;
                }
            }
        }
    }

    private void updatePosition(Direction direction, Tank player) {
        switch (direction) {
            case UP:
                player.y += GameSettings.TANK_MOVE_SIZE;
                break;
            case DOWN:
                player.y -= GameSettings.TANK_MOVE_SIZE;
                break;
            case LEFT:
                player.x -= GameSettings.TANK_MOVE_SIZE;
                break;
            case RIGHT:
                player.x += GameSettings.TANK_MOVE_SIZE;
                break;
        }
    }
}
