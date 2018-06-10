package karabaka.game.client;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;

public class KarabakaClient extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        EntityContainer.instance.addTank(new Tank(40,70,Direction.LEFT));
    }
}
