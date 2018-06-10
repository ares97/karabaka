package karabaka.game.client;

import karabaka.game.BaseGameRenderer;
import karabaka.game.client.network.DatagramClientSender;
import karabaka.game.client.utils.NetworkSettings;

public class KarabakaClient extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
//        EntityContainer.instance.setPlayer(new Player(new Tank(80, 160, Direction.UP), new ClientMoveHandlerImpl()));
        DatagramClientSender.instance.startServerListening();
        //DatagramClientSender.instance.tryConnectToServer();
    }

    @Override
    public void render() {
        super.render();
        if (!DatagramClientSender.instance.canJoin)
            DatagramClientSender.instance.sendDatagram(NetworkSettings.TRY_JOIN_TO_SERVER);
    }
}
