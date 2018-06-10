package karabaka.game.client.network;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Action;
import karabaka.game.client.utils.Direction;

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

    private DatagramParser() {
    }

}
