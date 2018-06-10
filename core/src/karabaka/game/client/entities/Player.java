package karabaka.game.client.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import karabaka.game.client.handlers.MoveHandler;
import karabaka.game.client.utils.Direction;

public class Player {

    private final Tank tank;
    private final MoveHandler moveHandler;

    public Player(Tank tank, MoveHandler moveHandler) {
        this.tank = tank;
        this.moveHandler = moveHandler;
    }

    public void handlePlayerInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            move((Direction.LEFT));
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            move(Direction.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            move(Direction.DOWN);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            move(Direction.RIGHT);
        }
    }

    private void move(Direction direction) {
        moveHandler.move(direction, tank);
    }

}
