package karabaka.game.client.handlers;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;

public interface MoveHandler {

    void move(Direction direction, Tank tank);
}
