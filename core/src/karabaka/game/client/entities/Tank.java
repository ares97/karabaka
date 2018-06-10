package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tank extends Rectangle {

    private Texture texture;

    public Tank(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("core/assets/Tank_up.png");
    }


}
