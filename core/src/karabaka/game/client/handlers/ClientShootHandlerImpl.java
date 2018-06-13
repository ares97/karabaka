package karabaka.game.client.handlers;

import karabaka.game.common.entities.Tank;
import karabaka.game.common.utils.DatagramParser;
import karabaka.game.common.handlers.ShootHandler;
import karabaka.game.common.utils.constants.Action;
import karabaka.game.common.utils.constants.Direction;
import karabaka.game.common.utils.constants.GameSettings;

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
