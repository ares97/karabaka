package karabaka.game.common.handlers;

import karabaka.game.common.entities.Tank;
import karabaka.game.common.utils.constants.Direction;

public interface ShootHandler {

    void shoot(Direction direction, Tank tank);
}
