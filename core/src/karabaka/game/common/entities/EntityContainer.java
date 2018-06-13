package karabaka.game.common.entities;

import karabaka.game.client.entities.Player;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EntityContainer {

    public static final EntityContainer instance = new EntityContainer();

    private ConcurrentLinkedQueue<Bullet> bullets;
    private Set<Tank> tanks;
    private Player player;

    private final Object tankLock = new Object();


    private EntityContainer() {
        bullets = new ConcurrentLinkedQueue<Bullet>();
        tanks = new HashSet<>();
    }


    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ConcurrentLinkedQueue<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void addTank(Tank tank) {
        synchronized (tankLock) {
            tanks.add(tank);
        }
    }

    public Set<Tank> getTanks() {
        synchronized (tankLock) {
            return tanks;
        }
    }

    public Optional<Tank> getTank(String uuid) {
        synchronized (tankLock) {
            return tanks.parallelStream().filter(t -> t.getId().equals(uuid)).findAny();
        }
    }

    public void setTanks(Set<Tank> tanks) {
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
