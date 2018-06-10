package karabaka.game.client.entities;

import karabaka.game.client.handlers.MoveHandler;
import karabaka.game.client.handlers.ClientMoveHandlerImpl;
import karabaka.game.client.utils.Direction;

public class Player {

    private final Tank tank;
    private final MoveHandler moveHandler;

    public Player(Tank tank) {
        this.tank = tank;
        this.moveHandler = new ClientMoveHandlerImpl(tank);
    }

    public void move(Direction direction) {
        moveHandler.move(direction);
    }

}
