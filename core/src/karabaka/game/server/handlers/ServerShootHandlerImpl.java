package karabaka.game.server.handlers;

import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.handlers.ShootHandler;
import karabaka.game.client.utils.Direction;

public class ServerShootHandlerImpl implements ShootHandler {
    @Override
    public void shoot(Direction direction, Tank tank) {
        Bullet bullet = new Bullet(tank.x, tank.y, direction);
        EntityContainer.instance.addBullet(bullet);
    }


}
