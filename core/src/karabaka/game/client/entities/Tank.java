package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import karabaka.game.client.utils.Direction;

import java.util.UUID;

public class Tank extends Rectangle {

    private Texture texture;
    private Direction direction;
    private String uuid = UUID.randomUUID().toString();

    public Tank(float x, float y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;

        texture = new Texture("core/assets/Tank_up.png");
    }

}
