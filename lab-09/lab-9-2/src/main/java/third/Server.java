package third;


import second2.ServerThread;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        try {
            DatagramSocket socket = new DatagramSocket(3000);

            byte[] buf = new byte[100];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println(received);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
