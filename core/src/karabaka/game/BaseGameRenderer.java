package karabaka.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Renderable;
import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.GameSettings;
import karabaka.game.client.utils.TextureManager;

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
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(map, 0, 0, GameSettings.GAME_WIDHT, GameSettings.GAME_HEIGHT);
        renderEntities();
        batch.end();
    }

    private void renderEntities() {
        for (Tank tank : EntityContainer.instance.getTanks())
            batch.draw(tank.getTexture(), tank.x, tank.y);

        for (Bullet bullet : EntityContainer.instance.getBullets())
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
