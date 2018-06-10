package karabaka.game.client.network;

import com.badlogic.gdx.Gdx;
import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.handlers.MoveHandler;
import karabaka.game.client.handlers.ShootHandler;
import karabaka.game.client.utils.Action;
import karabaka.game.client.utils.Direction;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;

import static karabaka.game.client.network.DatagramClientSender.PLAYER_IP;

public class DatagramParser {

    public final static DatagramParser instance = new DatagramParser();

    // movement parser: action-tankUUID-direction-
    public String parseAction(Direction direction, Tank tank, Action action) {
        String result = "";

        result += action;
        result += "&";
        result += tank.getId();
        result += "&";
        result += direction;

        return result;
    }

    public Runnable decodeAction(String datagram, MoveHandler moveHandler, ShootHandler shootHandler) {
        String[] message = datagram.split("&");
        if (message.length == 3) {
            Action action = Action.valueOf(message[0]);
            Direction direction = Direction.valueOf(message[2]);
            String uuid = message[1];

            Optional<Tank> tank = EntityContainer.instance.getTank(uuid);
            if (tank.isPresent()) {
                switch (action) {
                    case MOVE:
                        return () -> moveHandler.move(direction, tank.get());
                    case SHOOT:
                        return () -> shootHandler.shoot(direction, tank.get());
                }
            }
        }
        return getEmptyRunnable();
    }

    public synchronized void decodeEntities(String datagram) {
        String[] entities = datagram.split(":");

        if (entities.length >= 1) {
            String[] tankPackage = entities[0].split("-");
            decodeTankPackage(tankPackage);
        }
        if (entities.length >= 2) {
            String[] bulletPackage = entities[1].split("-");
            decodeBulletPackage(bulletPackage);
            System.out.println("bullet!");
        }
    }

    public synchronized String parseEntities() {
        //string = player[i].x + "\" + player[i].y + "\" + ... + ":" bullet[i].x + "\" + bullet[i].y + "\"
        String datagram = "";
        for (Tank player : EntityContainer.instance.getTanks()) {
            datagram += String.valueOf(player.x) + "-" + String.valueOf(player.y) + "-" +
                    String.valueOf(player.getDirection()) + "-" + player.getId() + "-";
        }
        datagram += ":";
        for (Bullet bullet : EntityContainer.instance.getBullets()) {
            datagram += String.valueOf(bullet.x) + "-" + String.valueOf(bullet.y) + "-"
                    + String.valueOf(bullet.getDirection()) + "-";
        }
        System.out.println(datagram);
        return datagram;
    }

    private synchronized static void decodeBulletPackage(String[] bulletPackage) {
        LinkedList<Bullet> bullets = new LinkedList<>();

        Gdx.app.postRunnable(() -> {
            for (int i = 0; i < bulletPackage.length; i += 3) {
                bullets.add(new Bullet(
                                Float.valueOf(bulletPackage[i]),
                                Float.valueOf(bulletPackage[i + 1]),
                                Direction.valueOf(bulletPackage[i + 2])
                        )
                );
            }
        });

        EntityContainer.instance.setBullets(bullets);
    }

    private synchronized static void decodeTankPackage(String[] tankPackage) {
        HashSet<Tank> tanks = new HashSet<>();
        Gdx.app.postRunnable(() -> {
            for (int i = 0; i < tankPackage.length; i += 4) {
                Tank tank = new Tank(
                        Float.valueOf(tankPackage[i]),
                        Float.valueOf(tankPackage[i + 1]),
                        Direction.valueOf(tankPackage[i + 2]),
                        tankPackage[i + 3]
                );
                if (tank.getId().equals(PLAYER_IP)) {
                    EntityContainer.instance.getPlayer().setTank(tank);
                }
                //   System.out.println(tank.getId());
                tanks.add(tank);
            }
        });
        EntityContainer.instance.setTanks(tanks);
    }

    private DatagramParser() {
    }

    private Runnable getEmptyRunnable() {
        return () -> {
        };
    }

}
