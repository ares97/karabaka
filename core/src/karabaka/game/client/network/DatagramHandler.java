package karabaka.game.client.network;

import karabaka.game.client.utils.NetworkSettings;

import java.net.*;

public class DatagramHandler {

    private DatagramSocket socket;

    public final static DatagramHandler instance = new DatagramHandler();

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


    private DatagramHandler() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
