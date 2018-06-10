package karabaka.game.client;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.entities.Player;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.handlers.ClientMoveHandlerImpl;
import karabaka.game.client.utils.Direction;

public class KarabakaClient extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        EntityContainer.instance.addTank(new Tank(40, 70, Direction.LEFT));
        EntityContainer.instance.setPlayer(new Player(new Tank(80, 160, Direction.UP), new ClientMoveHandlerImpl()));
    }

    @Override
    public void render() {
        super.render();
        EntityContainer.instance.getPlayer().handlePlayerInput();
    }
}
