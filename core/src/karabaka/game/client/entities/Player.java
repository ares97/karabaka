package karabaka.game.client.entities;

import karabaka.game.client.handlers.MoveHandler;
import karabaka.game.client.utils.Direction;

public class Player {

    private final Tank tank;
    private final MoveHandler moveHandler;

    public Player(Tank tank, MoveHandler moveHandler) {
        this.tank = tank;
        this.moveHandler = moveHandler;
    }

    public void move(Direction direction) {
        moveHandler.move(direction, tank);
    }

}
