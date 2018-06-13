package karabaka.game.client;

import karabaka.game.common.BaseGameRenderer;
import karabaka.game.client.handlers.DatagramClientHandler;
import karabaka.game.common.utils.constants.NetworkSettings;
import karabaka.game.common.entities.EntityContainer;

public class KarabakaClient extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        DatagramClientHandler.instance.startServerListening();
    }

    @Override
    public void render() {
        super.render();
        if (!DatagramClientHandler.instance.canJoin) {
            DatagramClientHandler.instance.sendDatagram(NetworkSettings.TRY_JOIN_TO_SERVER);
        } else {
            if (EntityContainer.instance.getPlayer() != null)
                EntityContainer.instance.getPlayer().handlePlayerInput();
        }
    }
}
