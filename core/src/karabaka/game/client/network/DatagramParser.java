package karabaka.game.client.network;

import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.handlers.MoveHandler;
import karabaka.game.client.handlers.ShootHandler;
import karabaka.game.client.utils.Action;
import karabaka.game.client.utils.Direction;

import java.util.Optional;

public class DatagramParser {

    public final static DatagramParser instance = new DatagramParser();

    // movement parser: action-tankUUID-direction-
    public String parseAction(Direction direction, Tank tank, Action action) {
        String result = "";

        result += action;
        result += "&";
        result += tank.getUuid();
        result += "&";
        result += direction;

        return result;
    }

    public Runnable decodeAction(String datagram, MoveHandler moveHandler, ShootHandler shootHandler) {
        String[] message = datagram.split("&");

        Action action = Action.valueOf(message[0]);
        Direction direction = Direction.valueOf(message[2]);
        String uuid = message[1];

        Optional<Tank> tank = EntityContainer.instance.getTank(uuid);
        if (tank.isPresent()) {
            switch (action) {
                case MOVE:
                    return () -> moveHandler.move(direction, tank.get());
                case SHOOT:
                    return () -> shootHandler.shoot(direction, tank.get());
            }
        }
        return () -> {
        };
    }

    private DatagramParser() {
    }

}
