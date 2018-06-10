package karabaka.game.server;

import karabaka.game.BaseGameRenderer;
import karabaka.game.server.handlers.DatagramServerReceiver;

public class KarabakaServer extends BaseGameRenderer {

    @Override
    public void create() {
        super.create();
        DatagramServerReceiver.instance.startListening();
    }

    @Override
    public void render() {
        super.render();
    }
}