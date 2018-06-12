package karabaka.game.client.handlers;

import karabaka.game.client.entities.Tank;
import karabaka.game.client.network.DatagramClientHandler;
import karabaka.game.client.network.DatagramParser;
import karabaka.game.client.utils.Action;
import karabaka.game.client.utils.Direction;
import karabaka.game.client.utils.GameSettings;

import java.util.Timer;
import java.util.TimerTask;

public class ClientShootHandlerImpl implements ShootHandler {

    boolean canShoot = true;

    @Override
    public void shoot(Direction direction, Tank tank) {
        if (canShoot) {
            handleCooldown();
            String encodedAction = DatagramParser.instance.parseAction(direction, tank, Action.SHOOT);
            DatagramClientHandler.instance.sendDatagram(encodedAction);
        }
    }

    private void handleCooldown() {
        canShoot = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canShoot = true;
            }
        }, GameSettings.BULLET_COOLDOWN_MS);
    }
}
