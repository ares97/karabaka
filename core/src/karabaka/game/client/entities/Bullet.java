package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
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
        texture = TextureManager.instance.bullet;
    }

    public Texture getTexture() {
        return texture;
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
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

