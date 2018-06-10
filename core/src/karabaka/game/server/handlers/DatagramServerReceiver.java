package karabaka.game.server.handlers;

import com.badlogic.gdx.Gdx;
import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Tank;
import karabaka.game.client.utils.Direction;
import karabaka.game.client.utils.NetworkSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

public class DatagramServerReceiver {

    private DatagramSocket socket;

    public final static DatagramServerReceiver instance = new DatagramServerReceiver();

    private final Set<InetAddress> players = new HashSet<>();

    public void startListening() {
        new Thread(datagramActionReceiver()).start();
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
                        //DatagramParser.instance.decodeAction().run();
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
            sendDatagram(NetworkSettings.JOIN_ACCEPTED, address);
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


    private DatagramServerReceiver() {
        try {
            socket = new DatagramSocket(NetworkSettings.SERVER_RECEIVER_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
