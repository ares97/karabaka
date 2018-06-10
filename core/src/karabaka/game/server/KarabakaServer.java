package karabaka.game.server;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;
import karabaka.game.server.handlers.DatagramServerReceiver;

public class KarabakaServer extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        DatagramServerReceiver.instance.startListening();
        EntityContainer.instance.addTank(new Tank(70,125,Direction.UP,"test"));
        EntityContainer.instance.addTank(new Tank(170,225,Direction.LEFT,"test2"));
        EntityContainer.instance.addTank(new Tank(310,400,Direction.DOWN,"test3"));
    }

    @Override
    public void render() {
        DatagramServerReceiver.instance.sendDataToPlayers().run();
        super.render();
        updateEntities();
    }

    private void updateEntities() {
        for (Bullet bullet : EntityContainer.instance.getBullets()) {
            bullet.update();
        }
    }
}