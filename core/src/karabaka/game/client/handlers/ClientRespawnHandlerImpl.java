package karabaka.game.client.handlers;

import karabaka.game.common.entities.Tank;
import karabaka.game.common.handlers.RespawnHandler;
import karabaka.game.common.utils.DatagramParser;
import karabaka.game.common.utils.constants.Action;
import karabaka.game.common.utils.constants.GameSettings;

import java.util.Timer;
import java.util.TimerTask;

public class ClientRespawnHandlerImpl implements RespawnHandler {

    private boolean canRespawn = true;


    @Override
    public void respawn(Tank tank) {
        if (canRespawn) {
            handleCooldown();
            String encodedAction = DatagramParser.instance.parseAction(tank.getDirection(), tank, Action.RESPAWN);
            DatagramClientHandler.instance.sendDatagram(encodedAction);
        }
    }

    private void handleCooldown() {
        canRespawn = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canRespawn = true;
            }
        }, GameSettings.RESPAWN_COOLDOWN_MS);
    }
}
