package karabaka.game.client.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import karabaka.game.client.handlers.ClientMoveHandlerImpl;
import karabaka.game.client.handlers.ClientShootHandlerImpl;
import karabaka.game.client.handlers.MoveHandler;
import karabaka.game.client.handlers.ShootHandler;
import karabaka.game.client.utils.Direction;

public class Player {

    private Tank tank;
    private MoveHandler moveHandler;
    private ShootHandler shootHandler;

    public Player(Tank tank, MoveHandler moveHandler, ShootHandler shootHandler) {
        this.tank = tank;
        this.moveHandler = moveHandler;
        this.shootHandler = shootHandler;
    }

    public Player() {
        moveHandler = new ClientMoveHandlerImpl();
        shootHandler = new ClientShootHandlerImpl();
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
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            shootHandler.shoot(tank.getDirection(), tank);
        }
    }

    private void move(Direction direction) {
        if (tank != null) {
            moveHandler.move(direction, tank);
        }
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }
}
