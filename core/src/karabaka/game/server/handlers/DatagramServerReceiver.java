package karabaka.game.server.handlers;

import karabaka.game.client.utils.NetworkSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServerReceiver {

    private DatagramSocket socket;

    public final static DatagramServerReceiver instance = new DatagramServerReceiver();

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
                    System.out.println(receivedDatagram);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private DatagramServerReceiver() {
        try {
            socket = new DatagramSocket(NetworkSettings.SERVER_RECEIVER_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
