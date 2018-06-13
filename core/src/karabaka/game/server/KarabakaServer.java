package karabaka.game.server;

import karabaka.game.common.BaseGameRenderer;
import karabaka.game.common.entities.EntityContainer;
import karabaka.game.common.entities.Bullet;
import karabaka.game.common.entities.Tank;
import karabaka.game.common.utils.constants.Direction;
import karabaka.game.server.handlers.DatagramServerHandler;

public class KarabakaServer extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        DatagramServerHandler.instance.startListening();
    }

    @Override
    public void render() {
        DatagramServerHandler.instance.sendDataToPlayers().run();
        //super.render();
        updateEntities();
    }

    private void updateEntities() {
        for (Bullet bullet : EntityContainer.instance.getBullets()) {
            bullet.update();
        }
    }

    private void addTrashData() {
        EntityContainer.instance.addTank(new Tank(70, 125, Direction.UP, "test"));
        EntityContainer.instance.addTank(new Tank(170, 225, Direction.LEFT, "test2"));
        EntityContainer.instance.addTank(new Tank(310, 400, Direction.DOWN, "test3"));
    }
}