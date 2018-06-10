package karabaka.game.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Player;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;

public class KarabakaClient extends ApplicationAdapter {

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
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
