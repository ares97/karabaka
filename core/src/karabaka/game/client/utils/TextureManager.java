package karabaka.game.client.utils;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    public final Texture tankUp;
    public final Texture tankDown;
    public final Texture tankLeft;
    public final Texture tankRight;

    public final Texture map;
    public final Texture bullet;

    public static TextureManager instance = new TextureManager();

    private TextureManager() {
        tankUp = new Texture("core/assets/Tank_up.png");
        tankDown = new Texture("core/assets/Tank_down.png");
        tankLeft = new Texture("core/assets/Tank_left.png");
        tankRight = new Texture("core/assets/Tank_right.png");
        map = new Texture("core/assets/map.jpg");

        bullet = new Texture("core/assets/bullet.png");
    }

    public Texture getTank(Direction direction) {
        switch (direction) {
            case UP:
                return TextureManager.instance.tankUp;
            case DOWN:
                return TextureManager.instance.tankDown;
            case LEFT:
                return TextureManager.instance.tankLeft;
            case RIGHT:
                return TextureManager.instance.tankRight;
            default:
                return TextureManager.instance.tankLeft;
        }
    }
}
