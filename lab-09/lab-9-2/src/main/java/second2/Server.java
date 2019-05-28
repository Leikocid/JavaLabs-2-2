package second2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        new Server().start();
    }

    List<ServerThread> clients = new ArrayList<>();
    public void start() {
        try {
            ServerSocket server = new ServerSocket(3000);
            while (true) {
                Socket clientSocket = server.accept();

                System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " connected");

                ServerThread clientThread = new ServerThread(this, clientSocket);
                register(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register(ServerThread client) {
        for (ServerThread c: clients) {
            c.notify("Register new client: " + client.getClientSocket().getInetAddress() + ":" + client.getClientSocket().getPort());
        }
        clients.add(client);
    }

    public void unregister(ServerThread client) {
        clients.remove(client);
        for (ServerThread c: clients) {
            c.notify("Unregister client: " + client.getClientSocket().getInetAddress() + ":" + client.getClientSocket().getPort());
        }
    }
}
