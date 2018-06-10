package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import karabaka.game.client.utils.Direction;
import karabaka.game.client.utils.TextureManager;

import java.util.UUID;

public class Tank extends Rectangle {

    private Texture texture;
    private Direction direction;
    private String uuid = UUID.randomUUID().toString();

    public Tank(float x, float y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        texture = TextureManager.instance.getTank(direction);
    }

    public Texture getTexture() {
        return texture;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getUuid() {
        return uuid;
    }
}
