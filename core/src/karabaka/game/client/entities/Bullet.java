package karabaka.game.client.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends Rectangle {

    private Texture texture;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        texture = new Texture("core/assets/bullet.png");
    }

    public Texture getTexture() {
        return texture;
    }
}
