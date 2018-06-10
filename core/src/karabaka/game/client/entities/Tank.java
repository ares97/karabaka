package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import karabaka.game.client.utils.Direction;
import karabaka.game.client.utils.TextureManager;

import java.util.Objects;

public class Tank extends Rectangle {

    private Texture texture;
    private Direction direction;
    private String id;

    public Tank(float x, float y, Direction direction, String id) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.id = id;
        texture = TextureManager.instance.getTank(direction);
    }

    public Texture getTexture() {
        return texture;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tank tank = (Tank) o;
        return Objects.equals(id, tank.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }
}
