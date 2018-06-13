package karabaka.game.client.handlers;

import karabaka.game.common.entities.Tank;
import karabaka.game.common.utils.DatagramParser;
import karabaka.game.common.handlers.MoveHandler;
import karabaka.game.common.utils.constants.Action;
import karabaka.game.common.utils.constants.Direction;

public class ClientMoveHandlerImpl implements MoveHandler {

    @Override
    public void move(Direction direction, Tank tank) {
        String encodedAction = DatagramParser.instance.parseAction(direction, tank, Action.MOVE);
        DatagramClientHandler.instance.sendDatagram(encodedAction);
    }

}
