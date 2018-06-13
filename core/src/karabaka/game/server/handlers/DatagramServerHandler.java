package karabaka.game.server.handlers;

import com.badlogic.gdx.Gdx;
import karabaka.game.common.entities.EntityContainer;
import karabaka.game.common.entities.Tank;
import karabaka.game.common.handlers.MoveHandler;
import karabaka.game.common.handlers.RespawnHandler;
import karabaka.game.common.handlers.ShootHandler;
import karabaka.game.common.utils.DatagramParser;
import karabaka.game.common.utils.constants.Direction;
import karabaka.game.common.utils.constants.NetworkSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

public class DatagramServerHandler {

    private DatagramSocket socket;

    public final static DatagramServerHandler instance = new DatagramServerHandler();

    private final Set<InetAddress> players = new HashSet<>();

    private MoveHandler moveHandler = new ServerMoveHandlerImpl();
    private ShootHandler shootHandler = new ServerShootHandlerImpl();
    private RespawnHandler respawnHandler = new ServerRespawnHandlerImpl();

    public void startListening() {
        new Thread(datagramActionReceiver()).start();
    }

    public Runnable sendDataToPlayers() {
        return () -> {
            String parsedEntities = DatagramParser.instance.parseEntities();
            for (InetAddress ip : players) {
                sendDatagram(parsedEntities, ip);
            }
        };
    }

    private Runnable datagramActionReceiver() {
        return () -> {
            while (true) {
                byte[] buff = new byte[1024];
                DatagramPacket dPacket = new DatagramPacket(buff, 1024);
                try {
                    socket.receive(dPacket);
                    String receivedDatagram = new String(dPacket.getData(), 0, dPacket.getLength());
                    if (receivedDatagram.equals(NetworkSettings.TRY_JOIN_TO_SERVER)) {
                        handleJoiningToServer(dPacket.getAddress());
                    } else {
                        Gdx.app.postRunnable(DatagramParser.instance
                                .decodeAction(receivedDatagram, moveHandler, shootHandler, respawnHandler));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private synchronized void handleJoiningToServer(InetAddress address) {
        for (InetAddress player : players) {
            if (player.equals(address))
                return;
        }
        Gdx.app.postRunnable(() -> {
            Tank newTank = new Tank(250, 250, Direction.UP, address.toString());
            EntityContainer.instance.addTank(newTank);
            players.add(address);
            sendDatagram(NetworkSettings.JOIN_ACCEPTED + "&" + address, address);
        });
    }

    public void sendDatagram(String datagram, InetAddress receiverIP) {
        try {
            DatagramPacket datagramPacket =
                    new DatagramPacket(
                            datagram.getBytes(),
                            datagram.length(),
                            receiverIP,
                            NetworkSettings.CLIENT_RECEIVER_PORT
                    );
            socket.send(datagramPacket);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    private DatagramServerHandler() {
        try {
            socket = new DatagramSocket(NetworkSettings.SERVER_RECEIVER_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
