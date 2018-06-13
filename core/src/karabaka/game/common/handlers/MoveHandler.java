package karabaka.game.common.handlers;

import karabaka.game.common.entities.Tank;
import karabaka.game.common.utils.constants.Direction;

public interface MoveHandler {

    void move(Direction direction, Tank tank);
}
