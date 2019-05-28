package second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        try {
            Socket clientSocket = new Socket("localhost", 3000);

            System.out.println("second.Client: " + clientSocket);

            try (OutputStream outbound = clientSocket.getOutputStream(); BufferedReader inbound = new BufferedReader(new
                    InputStreamReader(clientSocket.getInputStream())); ) {
                String message;
                while ((message = inbound.readLine()) != null) {
                    System.out.println(message);
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
