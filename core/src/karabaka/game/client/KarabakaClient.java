package karabaka.game.client;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.network.DatagramClientHandler;
import karabaka.game.client.utils.NetworkSettings;

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
