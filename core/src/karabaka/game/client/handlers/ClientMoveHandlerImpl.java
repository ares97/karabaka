package karabaka.game.client.handlers;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.network.DatagramHandler;
import karabaka.game.client.network.DatagramParser;
import karabaka.game.client.utils.Action;
import karabaka.game.client.utils.Direction;

public class ClientMoveHandlerImpl implements MoveHandler {

    @Override
    public void move(Direction direction, Tank tank) {
        String encodedAction = DatagramParser.instance.parseAction(direction, tank, Action.MOVE);
        DatagramHandler.instance.sendDatagram(encodedAction);
    }

}
