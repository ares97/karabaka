package karabaka.game.client.network;

import karabaka.game.client.utils.NetworkSettings;

import java.io.IOException;
import java.net.*;

public class DatagramClientSender {

    private DatagramSocket socket;

    public final static DatagramClientSender instance = new DatagramClientSender();

    public boolean canJoin = false;

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

    private Runnable serverListener() {
        return () -> {
            while (!canJoin) {
                byte[] buff = new byte[1024];
                DatagramPacket dPacket = new DatagramPacket(buff, 1024);
                try {
                    socket.receive(dPacket);
                    String receivedDatagram = new String(dPacket.getData(), 0, dPacket.getLength());
                    System.out.println(receivedDatagram);
                    if (receivedDatagram.equals(NetworkSettings.JOIN_ACCEPTED))
                        canJoin = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    private DatagramClientSender() {
        try {
            socket = new DatagramSocket(NetworkSettings.CLIENT_RECEIVER_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void tryConnectToServer() {
        new Thread(() -> {
            while (!canJoin) {
                sendDatagram(NetworkSettings.TRY_JOIN_TO_SERVER);
            }
        });
    }
}
