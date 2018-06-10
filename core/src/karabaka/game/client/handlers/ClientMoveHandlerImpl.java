package karabaka.game.client.handlers;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;

public class ClientMoveHandlerImpl implements MoveHandler {

    @Override
    public void move(Direction direction, Tank tank) {
        String datagram = generateTankMoveDatagram(direction,tank);
    }

    // movement parser: tankUUID-direction-
    private String generateTankMoveDatagram(Direction direction, Tank tank) {
        String result = "";


        return result;
    }
}
