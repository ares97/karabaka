package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import karabaka.game.client.utils.TextureManager;

public class Bullet extends Rectangle {

    private Texture texture;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        texture = TextureManager.instance.bullet;
    }

    public Texture getTexture() {
        return texture;
    }
}
