package karabaka.game.client;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.network.DatagramClientSender;
import karabaka.game.client.utils.NetworkSettings;

public class KarabakaClient extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        DatagramClientSender.instance.startServerListening();
    }

    @Override
    public void render() {
        super.render();
        if (!DatagramClientSender.instance.canJoin) {
            DatagramClientSender.instance.sendDatagram(NetworkSettings.TRY_JOIN_TO_SERVER);
        } else {
            if (EntityContainer.instance.getPlayer() != null)
                EntityContainer.instance.getPlayer().handlePlayerInput();
        }
    }
}
