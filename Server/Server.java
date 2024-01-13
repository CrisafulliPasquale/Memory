package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.plaf.basic.BasicSliderUI.ComponentHandler;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server avviato. In attesa di connessioni...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuova connessione accettata: " + clientSocket);

                // Crea un nuovo thread per gestire la connessione del client
                ComponentHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Errore durante l'avvio del server: " + e.getMessage());
        }
    }
}
