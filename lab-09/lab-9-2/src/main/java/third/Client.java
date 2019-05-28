package third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Client {
    public static void main(String[] args){
        try {
           DatagramSocket clientSocket = new DatagramSocket();
           byte[] message = "Message from client via UDP".getBytes();
           clientSocket.send(new DatagramPacket(message, message.length, InetAddress.getLocalHost(), 3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
