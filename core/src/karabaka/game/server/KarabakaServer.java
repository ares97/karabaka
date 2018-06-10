package karabaka.game.server;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Bullet;
import karabaka.game.server.handlers.DatagramServerReceiver;

public class KarabakaServer extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        DatagramServerReceiver.instance.startListening();
    }

    @Override
    public void render() {
        DatagramServerReceiver.instance.sendDataToPlayers().run();
        super.render();
        for (Bullet bullet : EntityContainer.instance.getBullets()){
            bullet.updatePosition();
        }
    }
}