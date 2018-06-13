package karabaka.game.common;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import karabaka.game.common.entities.Bullet;
import karabaka.game.common.entities.EntityContainer;
import karabaka.game.common.entities.Tank;
import karabaka.game.common.utils.constants.GameSettings;
import karabaka.game.common.utils.TextureManager;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BaseGameRenderer extends ApplicationAdapter {

    private SpriteBatch batch;

    private Texture map;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = TextureManager.instance.map;
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(map, 0, 0, GameSettings.GAME_WIDHT, GameSettings.GAME_HEIGHT);
        renderEntities();
        batch.end();
    }

    private void renderEntities() {
        Set<Tank> tanks = EntityContainer.instance.getTanks();
        ConcurrentLinkedQueue<Bullet> bullets = EntityContainer.instance.getBullets();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Tank tank : tanks)
            batch.draw(tank.getTexture(), tank.x, tank.y);

        for (Bullet bullet : bullets)
            batch.draw(bullet.getTexture(), bullet.x, bullet.y);
    }

    @Override
    public void dispose() {
        batch.dispose();
        disposeEntityTextures();
    }

    private void disposeEntityTextures() {
        for (Tank tank : EntityContainer.instance.getTanks())
            tank.getTexture().dispose();
        for (Bullet bullet : EntityContainer.instance.getBullets())
            bullet.getTexture().dispose();
    }
}
