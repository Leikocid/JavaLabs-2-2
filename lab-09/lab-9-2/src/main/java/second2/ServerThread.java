package second2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread{
    private Server server;
    private Socket clientSocket;
    private PrintStream os; // передача
    private BufferedReader is; // прием

    public ServerThread(Server server, Socket socket) throws IOException {
        this.server = server;
        this.clientSocket = socket;
        os = new PrintStream(socket.getOutputStream());
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        int i = 0;
        String str;
        try {
            while ((str = is.readLine()) != null) {
                try {
                    int clientNumber = Integer.parseInt(str);
                    str = is.readLine();
                    if (clientNumber > 0 && server.clients.size() >= clientNumber) {
                        server.clients.get(clientNumber - 1).notify(str);
                    }
                } catch (NumberFormatException e) {}
          }
        } catch (IOException e) {
        } finally {
            disconnect(); // уничтожение потока
        }
    }

    public void disconnect() {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }

            server.unregister(this);
            System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void notify(String s) {
        os.println(s);
    }
}
