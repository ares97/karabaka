package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import karabaka.game.client.EntityContainer;
import karabaka.game.client.utils.Direction;
import karabaka.game.client.utils.GameSettings;
import karabaka.game.client.utils.TextureManager;

public class Bullet extends Rectangle {

    private Texture texture;
    private Direction direction;

    public Bullet(float x, float y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.width = GameSettings.BULLET_SIZE;
        this.height = GameSettings.BULLET_SIZE;
        texture = TextureManager.instance.bullet;
    }

    public Texture getTexture() {
        return texture;
    }

    public synchronized void update() {
        updatePosition();
        for (Tank tank : EntityContainer.instance.getTanks()) {
            if (tank.overlaps(this)) {
                tank.x = -50;
                tank.y = -50;
                EntityContainer.instance.getBullets().remove(this);
            }
        }
    }

    public void updatePosition() {
        switch (direction) {
            case RIGHT:
                x += GameSettings.BULLET_MOVE_SIZE;
                break;
            case LEFT:
                x -= GameSettings.BULLET_MOVE_SIZE;
                break;
            case DOWN:
                y -= GameSettings.BULLET_MOVE_SIZE;
                break;
            case UP:
                y += GameSettings.BULLET_MOVE_SIZE;
                break;
        }
        if (x < -100 || x > GameSettings.GAME_WIDHT + 100 || y < -100 || y > GameSettings.GAME_HEIGHT + 100)
            EntityContainer.instance.getBullets().remove(this);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

