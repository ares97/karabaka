package karabaka.game.client.network;

import karabaka.game.client.EntityContainer;
import karabaka.game.client.entities.Player;
import karabaka.game.client.utils.NetworkSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DatagramClientHandler {

    private DatagramSocket socket;

    public final static DatagramClientHandler instance = new DatagramClientHandler();

    public boolean canJoin = false;

    public static String PLAYER_IP = "";

    public void startServerListening() {
        new Thread(serverListener()).start();
    }

    public void sendDatagram(String datagram) {
        try {
            InetAddress receiverIP = InetAddress.getByName(NetworkSettings.SERVER_IP);
            DatagramPacket datagramPacket =
                    new DatagramPacket(
                            datagram.getBytes(),
                            datagram.length(),
                            receiverIP,
                            NetworkSettings.SERVER_RECEIVER_PORT
                    );
            socket.send(datagramPacket);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized Runnable serverListener() {
        return () -> {
            while (true) {
                byte[] buff = new byte[4096];
                DatagramPacket dPacket = new DatagramPacket(buff, 4096);
                try {
                    socket.receive(dPacket);
                    String receivedDatagram = new String(dPacket.getData(), 0, dPacket.getLength());
                    if (receivedDatagram.split("&")[0].equals(NetworkSettings.JOIN_ACCEPTED)) {
                        canJoin = true;
                        PLAYER_IP = receivedDatagram.split("&")[1];
                        EntityContainer.instance.setPlayer(new Player());
                    } else {
                        DatagramParser.instance.decodeEntities(receivedDatagram);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    private DatagramClientHandler() {
        try {
            socket = new DatagramSocket(NetworkSettings.CLIENT_RECEIVER_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
