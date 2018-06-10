package karabaka.game.client;

import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Player;
import karabaka.game.client.entities.Tank;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EntityContainer {

    public static final EntityContainer instance = new EntityContainer();

    private List<Bullet> bullets;
    private List<Tank> tanks;
    private Player player;

    private final Object bulletLock = new Object();
    private final Object tankLock = new Object();


    private EntityContainer() {
        bullets = new LinkedList<>();
        tanks = new LinkedList<>();
    }


    public void addBullet(Bullet bullet) {
        synchronized (bulletLock) {
            bullets.add(bullet);
        }
    }

    public List<Bullet> getBullets() {
        synchronized (bulletLock) {
            return bullets;
        }
    }

    public void setBullets(List<Bullet> bullets) {
        synchronized (bulletLock) {
            this.bullets = bullets;
        }
    }

    public void addTank(Tank tank){
        synchronized (tankLock){
            tanks.add(tank);
        }
    }

    public List<Tank> getTanks() {
        synchronized (tankLock) {
            return tanks;
        }
    }

    public Optional<Tank> getTank(String uuid){
        synchronized (tankLock){
            return tanks.parallelStream().filter(t -> t.getUuid().equals(uuid)).findAny();
        }
    }

    public void setTanks(List<Tank> tanks) {
        synchronized (tankLock) {
            this.tanks = tanks;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
