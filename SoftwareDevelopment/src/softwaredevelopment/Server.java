/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Niels Riemersma (Jan ter Schure)
 */
public class Server extends Thread {

    ServerFrame frame = new ServerFrame();
    private ServerSocket serverSocket;
    private Server.onMessageReceived messageListener;
    private final int SERVERPORT = 4443;
    private boolean running = false;
    private PrintWriter outputWriter;
    private BufferedReader inputReader;

    public Server(Server.onMessageReceived messagelistener) {
        messageListener = messagelistener;
    }

    @Override
    public void run() {
        super.run();

        running = true;

        try {
            System.out.println("Server: Attempting to accept client connection.");

            //Create a server socket on port 4443 (10 connect attempts max.)
            serverSocket = new ServerSocket(SERVERPORT, 10);

            //Create client socket and accept() the incoming client.
            Socket socket = serverSocket.accept();
            System.out.println("Server: Connection with client established!");

            try {
                //Sends a message to the client
                outputWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                //Reads the message received from client                
                inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //This while listens for messages
                while (running) {
                    String message = inputReader.readLine();

                    if (message != null && messageListener != null) {
                        messageListener.messageReceived(message);
                    }
                }
            } catch (Exception e) {
                System.out.println("Server: Send/Receive error.");
                e.printStackTrace();
            } finally {
                socket.close();
                System.out.println("Server: Finished.");
            }
        } catch (Exception e) {
            System.out.println("Server: Could not connect to client.");
            e.printStackTrace();
        }
    }

    public interface onMessageReceived {

        public void messageReceived(String message);
    }

    public void send(String sendString) {
        try {
            outputWriter.println(sendString);
        } catch (Exception ex) {
            System.out.println("Server: Could not send.");
        }
    }
}
