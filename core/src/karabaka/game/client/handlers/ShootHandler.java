package karabaka.game.client.handlers;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;

public interface ShootHandler {

    void shoot(Direction direction, Tank tank);
}
