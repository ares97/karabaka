package karabaka.game.client.handlers;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;

public class ClientMoveHandlerImpl implements MoveHandler {

    @Override
    public void move(Direction direction, Tank tank) {
        switch (direction) {
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
        }
    }
}
