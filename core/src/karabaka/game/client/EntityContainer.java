package karabaka.game.client;

import karabaka.game.client.entities.Bullet;
import karabaka.game.client.entities.Player;
import karabaka.game.client.entities.Tank;

import java.util.*;

public class EntityContainer {

    public static final EntityContainer instance = new EntityContainer();

    private List<Bullet> bullets;
    private Set<Tank> tanks;
    private Player player;

    private final Object bulletLock = new Object();
    private final Object tankLock = new Object();


    private EntityContainer() {
        bullets = new LinkedList<>();
        tanks = new HashSet<>();
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

    public Set<Tank> getTanks() {
        synchronized (tankLock) {
            return tanks;
        }
    }

    public Optional<Tank> getTank(String uuid){
        synchronized (tankLock){
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
