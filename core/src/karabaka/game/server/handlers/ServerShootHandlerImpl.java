package karabaka.game.server.handlers;

import karabaka.game.common.entities.EntityContainer;
import karabaka.game.common.entities.Bullet;
import karabaka.game.common.entities.Tank;
import karabaka.game.common.handlers.ShootHandler;
import karabaka.game.common.utils.constants.Direction;
import karabaka.game.common.utils.constants.GameSettings;

public class ServerShootHandlerImpl implements ShootHandler {
    @Override
    public void shoot(Direction direction, Tank tank) {
        Bullet bullet = getBullet(direction, tank);
        EntityContainer.instance.addBullet(bullet);
    }

    private Bullet getBullet(Direction direction, Tank tank) {
        int x = (int) tank.x;
        int y = (int) tank.y;
        switch (direction) {
            case UP:
                y += GameSettings.TANK_SIZE;
                break;
            case DOWN:
                y -= GameSettings.TANK_SIZE;
                break;
            case LEFT:
                x -= GameSettings.TANK_SIZE;
                break;
            case RIGHT:
                x += GameSettings.TANK_SIZE;
                break;
        }

        return new Bullet(x, y, direction);
    }


}
